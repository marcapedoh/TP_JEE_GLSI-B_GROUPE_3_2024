package tp.jEE.Groupe3.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import tp.jEE.Groupe3.Controller.API.TransactionAPI;
import tp.jEE.Groupe3.DAO.TransactionDAO;
import tp.jEE.Groupe3.Service.TransactionServices;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TransactionController implements TransactionAPI {
    private TransactionServices transactionServices;

    @Autowired
    public TransactionController(TransactionServices transactionServices) {
        this.transactionServices = transactionServices;
    }
    @Override
    public TransactionDAO findById(Integer id) {
        return transactionServices.findById(id);
    }

    @Override
    public TransactionDAO findByLibelle(String libelle) {
        return transactionServices.findByLibelle(libelle);
    }

    @Override
    public List<TransactionDAO> findAll() {
        return transactionServices.findAll();
    }

    @Override
    public void delete(Integer id) {
        transactionServices.delete(id);
    }
}
