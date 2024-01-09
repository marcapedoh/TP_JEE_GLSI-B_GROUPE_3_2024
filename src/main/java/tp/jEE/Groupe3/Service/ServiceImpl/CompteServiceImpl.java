package tp.jEE.Groupe3.Service.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.jEE.Groupe3.DAO.CompteDAO;
import tp.jEE.Groupe3.Exception.EntityNotFoundException;
import tp.jEE.Groupe3.Exception.ErrorCodes;
import tp.jEE.Groupe3.Exception.InvalidEntityException;
import tp.jEE.Groupe3.Exception.InvalidOperationException;
import tp.jEE.Groupe3.Repository.CompteRepository;
import tp.jEE.Groupe3.Service.CompteServices;
import tp.jEE.Groupe3.Validator.CompteValidator;
import tp.jEE.Groupe3.models.Compte;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static tp.jEE.Groupe3.models.TypeCompte.COURANT;
import static tp.jEE.Groupe3.models.TypeCompte.EPARGNE;

@Service
@Slf4j
public class CompteServiceImpl implements CompteServices {
    private CompteRepository compteRepository;

    @Autowired
    public CompteServiceImpl(CompteRepository compteRepostory) {
        this.compteRepository = compteRepostory;
    }

    @Override
    public CompteDAO save(CompteDAO compteDAO) {
        List<String> errors= CompteValidator.validate(compteDAO);
        if(!errors.isEmpty()){
            log.warn("le compte que vous passez n'est pas valid{}",compteDAO);
            throw  new InvalidEntityException("Compte non Valid", ErrorCodes.COMPTE_NOT_VALID);
        }
        return CompteDAO.fromEntity(
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
    public CompteDAO findByNumeroCpt(Iban iban) {
        if(iban==null){
            log.error("le numero de compte ne doit pas être null");
        }
        return compteRepository.findCompteByNumeroCpt(iban)
                .map(CompteDAO::fromEntity).orElseThrow(()->
                        new EntityNotFoundException("aucun compte n'est trouvé pour numero de compte",ErrorCodes.COMPTE_NOT_FOUND));
    }

    @Override
    public boolean rechargerCompte(double montant, Iban iban) {
        if(montant<0){
            log.error("vous ne pouvez pas recharger un compte avec un montant negatif");

        }
        if(iban==null){
            log.warn("le numero de compte doit etre fourni pour faire un depot/rechargement");

        }
        Compte compte= compteRepository.findCompteByNumeroCpt(iban).get();
        if(compte.getTypeCompte()==EPARGNE && montant>5000000){
            log.warn("pour un compte épargne vous ne pouvez pas faire un depot de "+montant);
            throw new InvalidOperationException("opération invalide",ErrorCodes.COMPTE_NOT_AVAIBLE_FOR_THIS_OPERATION);

        }else if(compte.getTypeCompte()==COURANT && montant>50000000){
            log.warn("même pour le compte courant vous ne pouvez pas depassé un certain plafond");
            throw new InvalidOperationException("opération invalide",ErrorCodes.COMPTE_NOT_AVAIBLE_FOR_THIS_OPERATION);

        }
        compte.setSolde(compte.getSolde()+montant);
        return true;
    }

    @Override
    public boolean faireRetrait(double montant, Iban iban) {
        if(montant<0){
            log.error("vous ne pouvez pas recharger un compte avec un montant negatif");
        }
        if(iban==null){
            log.warn("le numero de compte doit etre fourni pour faire un depot/rechargement");

        }
        Compte compte= compteRepository.findCompteByNumeroCpt(iban).get();
        if(compte.getTypeCompte()==EPARGNE && montant>5000000){
            log.warn("pour un compte épargne vous ne pouvez pas faire un retrait de "+montant);
            throw new InvalidOperationException("opération invalide",ErrorCodes.COMPTE_NOT_AVAIBLE_FOR_THIS_OPERATION);

        }else if(compte.getTypeCompte()==COURANT && montant>50000000){
            log.warn("même pour le compte courant vous ne pouvez pas depassé un certain plafond");
            throw new InvalidOperationException("opération invalide",ErrorCodes.COMPTE_NOT_AVAIBLE_FOR_THIS_OPERATION);

        }
        if(validCompteForRetrait(montant, compte)==false){
            log.warn("compte not valid for this operation");
        }
        compte.setSolde(compte.getSolde()-montant);
        return true;
    }

    private static boolean validCompteForRetrait(double montant, Compte compte) {
        if(montant > compte.getSolde()){
            log.warn("solde insuffisant sur votre compte pour pouvoir faire un retrait");
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
        if(compteRepository.findCompteByClientId(id)==null){
            throw new InvalidOperationException("Vous ne pouvez pas supprimé un compte qui relié à un client");
        }
        compteRepository.deleteById(id);
    }
}
