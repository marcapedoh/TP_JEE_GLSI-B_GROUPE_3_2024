package tp.jEE.Groupe3.Validator;

import org.springframework.util.StringUtils;
import tp.jEE.Groupe3.DAO.TransactionDAO;

import java.util.ArrayList;
import java.util.List;

public class TransactionValidator {
    public static List<String> validate(TransactionDAO transactionDAO){
        List<String> errors= new ArrayList<>();

        if(transactionDAO==null){
            errors.add("l'objet que vous passer est carrément null");
            errors.add("vous ne pouvez pas construire un objet transaction sans fournir le montant de la transaction");
            errors.add("vous créez une transaction sans fournir le client qui fait la transaction... vous voulez que la banque fasse faillite c'est ça ?♠♠○-");
            errors.add("on doit pouvoir savoir quel compte est associé à une transaction");

        }
        if(transactionDAO.getMontant()==null){
            errors.add("vous ne pouvez pas construire un objet transaction sans fournir le montant de la transaction");
        }

        if(transactionDAO.getClient()==null){
            errors.add("vous créez une transaction sans fournir le client qui fait la transaction... vous voulez que la banque fasse faillite c'est ça ?♠♠○-");
        }
        if(transactionDAO.getCompte()==null){
            errors.add("on doit pouvoir savoir quel compte est associé à une transaction");
        }
        if(!StringUtils.hasLength(transactionDAO.getLibelleTran())){
            errors.add("on doit pouvoir savoir quel compte est associé à une transaction");
        }
        return errors;
    }
}
