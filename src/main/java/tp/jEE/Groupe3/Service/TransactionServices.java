package tp.jEE.Groupe3.Service;

import tp.jEE.Groupe3.DAO.TransactionDAO;

import java.util.List;

public interface TransactionServices {
    TransactionDAO save(TransactionDAO transactionDAO);
    TransactionDAO findById(Integer id);
    TransactionDAO findByLibelle(String libelle);
    List<TransactionDAO> findAll();
    void delete(Integer id);
}
