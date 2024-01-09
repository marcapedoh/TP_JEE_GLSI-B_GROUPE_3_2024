package tp.jEE.Groupe3.Service.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tp.jEE.Groupe3.DAO.ClientDAO;
import tp.jEE.Groupe3.Exception.EntityNotFoundException;
import tp.jEE.Groupe3.Exception.ErrorCodes;
import tp.jEE.Groupe3.Exception.InvalidEntityException;
import tp.jEE.Groupe3.Repository.ClientRepository;
import tp.jEE.Groupe3.Service.ClientServices;
import tp.jEE.Groupe3.Validator.ClientValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientServices {
    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }



    @Override
    public ClientDAO findById(Integer id) {
        if(id==null){
            log.warn("comment voulez-vous faire une recherche si vous ne fournissez pas l'id");
        }
        return clientRepository.findById(id)
                .map(ClientDAO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("aucun client n'est trouvé en base pour cette id"));
    }

    @Override
    public ClientDAO findByNom(String nom) {
        if(!StringUtils.hasLength(nom)){
            log.error("la recherche ne peut pas aboutir parceque le nom est null");
        }
        return clientRepository.findClientByNom(nom)
                .map(ClientDAO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("aucun client n'est trouvé pour ce nom"));
    }

    @Override
    public List<ClientDAO> findALl() {
        return clientRepository.findAll().stream()
                .map(ClientDAO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            log.error("vous passez un id null pour faire la recherche");
        }
        if(clientRepository.findAllCompte(id)==null){
            log.error("vous ne pouvez pas supprimé cet objet parceque l'objet est relié à d'autre!");
        }
        assert id != null;
        clientRepository.deleteById(id);
    }
}
