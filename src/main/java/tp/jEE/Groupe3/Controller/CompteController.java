package tp.jEE.Groupe3.Controller;

import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import tp.jEE.Groupe3.Controller.API.CompteAPI;
import tp.jEE.Groupe3.DAO.CompteDAO;
import tp.jEE.Groupe3.DAO.TransactionDAO;
import tp.jEE.Groupe3.Service.CompteServices;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CompteController implements CompteAPI {
    private CompteServices compteServices;

    @Autowired
    public CompteController(CompteServices compteServices) {
        this.compteServices = compteServices;
    }

    @Override
    public CompteDAO save(CompteDAO compteDAO) {
        return compteServices.save(compteDAO);
    }

    @Override
    public CompteDAO findById(Integer id) {
        return compteServices.findById(id);
    }

    @Override
    public CompteDAO findByNumeroCpt(Iban iban) {
        return compteServices.findByNumeroCpt(iban);
    }

    @Override
    public boolean rechargerCompte(double montant, Iban iban) {
        return compteServices.rechargerCompte(montant,iban);
    }

    @Override
    public TransactionDAO faireVirement(Iban iban1, Iban iban2, double montant) {
        return compteServices.faireVirement(iban1,iban2,montant);
    }

    @Override
    public boolean faireRetrait(double montant, Iban iban) {
        return compteServices.faireRetrait(montant,iban);
    }

    @Override
    public List<CompteDAO> findAll() {
        return compteServices.findAll();
    }

    @Override
    public void delete(Integer id) {
        compteServices.delete(id);
    }
}
