package tp.jEE.Groupe3.Validator;

import org.springframework.util.StringUtils;
import tp.jEE.Groupe3.DAO.ClientDAO;
import tp.jEE.Groupe3.models.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validate(ClientDAO client){
        List<String> errors=new ArrayList<>();
        if(client==null){
            errors.add("vous passez carrement un client vide ou les informations n'arrive pas à etre recuperé"+client.toString());
        }
        if(!StringUtils.hasLength(client.getNom())){
            errors.add("vous devez fournir le nom du client");
        }
        if(!StringUtils.hasLength(client.getAdresse())){
            errors.add("l'adresse du client ne peux pas être null");
        }
        return errors;
    }
}
