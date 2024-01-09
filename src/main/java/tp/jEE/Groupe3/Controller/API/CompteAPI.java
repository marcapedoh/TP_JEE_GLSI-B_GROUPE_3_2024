package tp.jEE.Groupe3.Controller.API;

import org.iban4j.Iban;
import tp.jEE.Groupe3.DAO.CompteDAO;

import java.util.List;

public interface CompteAPI {

    CompteDAO save(CompteDAO compteDAO);
    CompteDAO findById(Integer id);
    CompteDAO findByNumeroCpt(Iban iban);
    boolean rechargerCompte(double montant, Iban iban);
    boolean faireRetrait(double montant,Iban iban);
    List<CompteDAO> findAll();
    void delete(Integer id);
}
