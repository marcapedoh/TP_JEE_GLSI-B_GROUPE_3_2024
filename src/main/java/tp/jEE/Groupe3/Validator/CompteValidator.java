package tp.jEE.Groupe3.Validator;

import tp.jEE.Groupe3.DAO.CompteDAO;

import java.util.ArrayList;
import java.util.List;

public class CompteValidator {
    public static List<String> validate(CompteDAO compteDAO){
        List<String> errors= new ArrayList<>();
        if(compteDAO==null){
            errors.add("vous passez un objet compte vide"+compteDAO.toString());
        }
        if(compteDAO.getSolde()==null){
            errors.add("vous ne pouvez pas créé un compte et ne rien ajouté sur le compte comme sous du moins pas comme ça dans notre banquqe!");
        }
        if(compteDAO.getNumeroCpt()==null){
            errors.add("bon là ne numero de compte doit enregistré");
        }
        return  errors;
    }
}
