package tp.jEE.Groupe3.Service;

import org.iban4j.Iban;
import tp.jEE.Groupe3.DAO.CompteDAO;
import tp.jEE.Groupe3.DAO.TransactionDAO;
import tp.jEE.Groupe3.models.Compte;

import java.math.BigDecimal;
import java.util.List;

public interface CompteServices {
    CompteDAO save(CompteDAO compteDAO);
    CompteDAO findById(Integer id);
    CompteDAO findByNumeroCpt(Iban iban);
    boolean rechargerCompte(double montant, Iban iban);
    TransactionDAO faireVirement(Iban iban1, Iban iban2, double montant);
    boolean faireRetrait(double montant,Iban iban);
    List<CompteDAO> findAll();
    void delete(Integer id);
}
