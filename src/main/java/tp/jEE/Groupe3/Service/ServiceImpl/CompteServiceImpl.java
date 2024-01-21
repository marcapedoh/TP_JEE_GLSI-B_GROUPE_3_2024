package tp.jEE.Groupe3.Service.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tp.jEE.Groupe3.DAO.CompteDAO;
import tp.jEE.Groupe3.DAO.TransactionDAO;
import tp.jEE.Groupe3.Exception.EntityNotFoundException;
import tp.jEE.Groupe3.Exception.ErrorCodes;
import tp.jEE.Groupe3.Exception.InvalidEntityException;
import tp.jEE.Groupe3.Exception.InvalidOperationException;
import tp.jEE.Groupe3.Repository.ClientRepository;
import tp.jEE.Groupe3.Repository.CompteRepository;
import tp.jEE.Groupe3.Repository.TransactionRepository;
import tp.jEE.Groupe3.Service.CompteServices;
import tp.jEE.Groupe3.Validator.CompteValidator;
import tp.jEE.Groupe3.models.Compte;
import tp.jEE.Groupe3.models.TypeTransaction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static tp.jEE.Groupe3.DAO.CompteDAO.fromEntity;
import static tp.jEE.Groupe3.models.TypeCompte.COURANT;
import static tp.jEE.Groupe3.models.TypeCompte.EPARGNE;

@Service
@Slf4j
public class CompteServiceImpl implements CompteServices {
    private final CompteRepository compteRepository;
    private final TransactionRepository transactionRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;

    @Autowired
    public CompteServiceImpl(CompteRepository compteRepostory, TransactionRepository transactionRepository, PasswordEncoder passwordEncoder, ClientRepository clientRepository) {
        this.compteRepository = compteRepostory;
        this.transactionRepository=transactionRepository;
        this.passwordEncoder = passwordEncoder;
        this.clientRepository=clientRepository;
    }

    @Override
    public CompteDAO save(CompteDAO compteDAO) {
        List<String> errors= CompteValidator.validate(compteDAO);
        if(!errors.isEmpty()){
            log.warn("le compte que vous passez n'est pas valid{}",compteDAO);
            throw  new InvalidEntityException("Compte non Valid", ErrorCodes.COMPTE_NOT_VALID);
        }
        compteDAO.setCodePin(passwordEncoder.encode(compteDAO.getCodePin()));
        return fromEntity(
                compteRepository.save(
                        CompteDAO.toEntity(compteDAO)
                )
        );
    }

    @Override
    public CompteDAO findById(Integer id) {
        if(id==null){
            log.warn("vous pasez un id null en parametre pour faire la recherche");
        }
        return compteRepository.findById(id)
                .map(CompteDAO::fromEntity).orElseThrow(()->
                        new EntityNotFoundException("aucun objet n'est trouvé en base de donné pour cette id")
                );
    }

    @Override
    public CompteDAO findByNumeroCpt(String iban) {
        if(iban==null){
            log.error("le numero de compte ne doit pas être null");
        }
        return compteRepository.findCompteByNumeroCpt(iban)
                .map(CompteDAO::fromEntity).orElseThrow(()->
                        new EntityNotFoundException("aucun compte n'est trouvé pour numero de compte",ErrorCodes.COMPTE_NOT_FOUND));
    }

    @Override
    public boolean rechargerCompte(double montant, String iban) {
        Compte compte = searchAccountAndValidAmmount(montant, iban);
        if (compte == null) return false;
        compte.setSolde(compte.getSolde()+montant);
        compteRepository.save(compte);
        transactionRepository.save(
                TransactionDAO.toEntity(
                        TransactionDAO.builder()
                                .dateCreation(Instant.now())
                                .montant(montant)
                                .libelleTran("dépôt/rechargement de "+montant+" sur le compte "+compte.getNumeroCpt())
                                .typeTransaction(TypeTransaction.DEPOT)
                                .compte(fromEntity(compte))
                                .build()
                )
        );
        return true;
    }

    private Compte searchAccountAndValidAmmount(double montant, String iban) {
        if(montant <=0){
            log.error("vous ne pouvez pas recharger un compte avec un montant negatif ou null");

        }
        if(!StringUtils.hasLength(iban)){
            log.warn("le numero de compte doit etre fourni pour faire un depot/rechargement");

        }
        Compte compte= compteRepository.findCompteByNumeroCpt(iban).get();
        if(compte==null){
            log.error("aucun compte n'est trouvé pour ce numero de compte");
            return null;
        }
        if(compte.getTypeCompte()==EPARGNE && montant >5000000){
            log.warn("pour un compte épargne vous ne pouvez pas faire un depot de "+ montant);
            throw new InvalidOperationException("opération invalide",ErrorCodes.COMPTE_NOT_AVAIBLE_FOR_THIS_OPERATION);

        }else if(compte.getTypeCompte()==COURANT && montant >50000000){
            log.warn("même pour le compte courant vous ne pouvez pas depassé un certain plafond");
            throw new InvalidOperationException("opération invalide",ErrorCodes.COMPTE_NOT_AVAIBLE_FOR_THIS_OPERATION);

        }
        return compte;
    }

    @Override
    public TransactionDAO faireVirement(String iban1, String iban2, double montant) {
        List<String> errors= validateParamaterInformation(iban1, iban2, montant);
        if(!errors.isEmpty()){
            throw new InvalidEntityException("les informations en parametre ne sont pas valide",ErrorCodes.ACCOUNT_BALANCE_NOT_SUPPORTED,errors);
        }
        Compte compte= compteRepository.findCompteByNumeroCpt(iban1).get();
        Compte compte1= compteRepository.findCompteByNumeroCpt(iban2).get();
        List<String> errors1 = verificationOfAccountsValidationForpoeration(compte, compte1);
        if(!errors1.isEmpty()){
            throw new InvalidEntityException("les informations en parametre pour les comptes ne sont pas valide",ErrorCodes.COMPTE_NOT_VALID,errors1);
        }
        if(compte.getSolde()<montant){
            throw new InvalidOperationException("vous ne pouvez pas faire un virement si le compte d'envoi a un montant inferieur au montant à envoyer",ErrorCodes.ACCOUNT_BALANCE_NOT_SUPPORTED);
        }
        compte.setSolde(compte.getSolde()-montant);
        compte1.setSolde(compte1.getSolde()+montant);
        compteRepository.save(compte);
        compteRepository.save(compte1);

        return TransactionDAO.fromEntity(
                transactionRepository.save(
                    TransactionDAO.toEntity(
                            TransactionDAO.builder()
                                    .dateCreation(Instant.now())
                                    .montant(montant)
                                    .libelleTran("Virement de "+montant+" sur le compte "+compte.getNumeroCpt())
                                    .typeTransaction(TypeTransaction.VIREMENT)
                                    .compte(fromEntity(compte))
                                    .build()
                )
        ));
    }

    private static List<String> validateParamaterInformation(String iban1, String iban2, double montant) {
        List<String> errors=new ArrayList<>();
        if(montant <0){
            errors.add("vous ne pouvez pas recharger un compte avec un montant negatif");
        }
        if(!StringUtils.hasLength(iban1)){
            errors.add("le numero de compte doit etre fourni pour faire un depot/rechargement");
        }
        if(!StringUtils.hasLength(iban2)){
            errors.add("le numero de compte doit etre fourni pour faire un depot/rechargement");
        }
        return errors;
    }

    private static List<String> verificationOfAccountsValidationForpoeration(Compte compte, Compte compte1) {
        List<String> errors=new ArrayList<>();
        if(!CompteValidator.validate(CompteDAO.fromEntity(compte)).isEmpty() && CompteValidator.validate(CompteDAO.fromEntity(compte1)).isEmpty()){
            errors.add("les comptes pour le depot et le compte de retrait ne sont pas valide");
        }
        if(!CompteValidator.validate(CompteDAO.fromEntity(compte)).isEmpty()){
            errors.add("le compte expéditeur n'est pas valide");
        }
        if(!CompteValidator.validate(CompteDAO.fromEntity(compte1)).isEmpty()){
            errors.add("le compte recepteur n'est pas valide");
        }
        return errors;
    }

    @Override
    public boolean faireRetrait(double montant, String iban) {
        Compte compte = validateNumberOfAccountAndValideAmount(montant, iban);
        if(validCompteForRetrait(montant, compte)==false){
            log.warn("compte not valid for this operation");
            return  false;
        }else{
            compte.setSolde(compte.getSolde()-montant);
            compteRepository.save(compte);
            transactionRepository.save(
                    TransactionDAO.toEntity(
                            TransactionDAO.builder()
                                    .dateCreation(Instant.now())
                                    .montant(montant)
                                    .libelleTran("Retrait du "+montant+" sur le compte "+compte.getNumeroCpt())
                                    .typeTransaction(TypeTransaction.RETRAIT)
                                    .compte(fromEntity(compte))
                                    .build()
                    )
            );
            return true;
        }

    }

    private Compte validateNumberOfAccountAndValideAmount(double montant, String iban) {
        List<String> errors= new ArrayList<>();
        if(montant <0){
            log.error("vous ne pouvez pas recharger un compte avec un montant negatif");
        }
        if(!StringUtils.hasLength(iban)){
            log.warn("le numero de compte doit etre fourni pour faire un depot/rechargement");

        }
        Compte compte= compteRepository.findCompteByNumeroCpt(iban).get();
        if(compte==null){
            log.error("Aucun compte ne correspond au numero de compte donné");
            errors.add("Aucun compte ne correspond au numero de compte donné");
        }
        if(compte.getTypeCompte()==EPARGNE && montant >5000000){
            if(compte.getSolde()<montant){
                errors.add("le montant que vous essayez de retirer même vous ne l'avez pas, il faut être humble!");
            }
            log.warn("pour un compte épargne vous ne pouvez pas faire un retrait de "+ montant);
            errors.add("pour un compte épargne vous ne pouvez pas faire un retrait de "+ montant);

            throw new InvalidOperationException("opération invalide",ErrorCodes.ACCOUNT_BALANCE_NOT_SUPPORTED,errors);

        }else if(compte.getTypeCompte()==COURANT && montant >50000000){
            if(compte.getSolde()<montant){
                errors.add("le montant que vous essayez de retirer même vous ne l'avez pas, il faut être humble!");
                throw new InvalidOperationException("opération invalide",ErrorCodes.COMPTE_NOT_AVAIBLE_FOR_THIS_OPERATION,errors);
            }
            log.warn("même pour le compte courant vous ne pouvez pas depassé un certain plafond");
            errors.add("même pour le compte courant vous ne pouvez pas depassé un certain plafond");
            throw new InvalidOperationException("opération invalide",ErrorCodes.COMPTE_NOT_AVAIBLE_FOR_THIS_OPERATION,errors);

        }
        return compte;
    }

    private static boolean validCompteForRetrait(double montant, Compte compte) {
        if(montant > compte.getSolde() || montant<0){
            log.warn("solde insuffisant sur votre compte pour pouvoir faire un retrait ou vous passez un montant négatif");
            return false;
        }else{
            return true;
        }
    }

    @Override
    public List<CompteDAO> findAll() {
        return compteRepository.findAll().stream()
                .map(CompteDAO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            log.warn("vous fournissez un id null");
        }

        assert id != null;
        compteRepository.deleteById(id);
    }
}
