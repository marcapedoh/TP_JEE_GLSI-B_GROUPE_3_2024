package tp.jEE.Groupe3.Service.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.jEE.Groupe3.DAO.CompteDAO;
import tp.jEE.Groupe3.Exception.EntityNotFoundException;
import tp.jEE.Groupe3.Exception.ErrorCodes;
import tp.jEE.Groupe3.Exception.InvalidEntityException;
import tp.jEE.Groupe3.Repository.CompteRepository;
import tp.jEE.Groupe3.Service.CompteServices;
import tp.jEE.Groupe3.Validator.CompteValidator;
import tp.jEE.Groupe3.models.Compte;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        compteRepository.deleteById(id);
    }
}
