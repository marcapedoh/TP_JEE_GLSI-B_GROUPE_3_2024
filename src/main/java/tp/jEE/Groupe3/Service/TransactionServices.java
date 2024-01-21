package tp.jEE.Groupe3.Service;

import tp.jEE.Groupe3.DAO.TransactionDAO;
import tp.jEE.Groupe3.models.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionServices {
    TransactionDAO findById(Integer id);
    TransactionDAO findByLibelle(String libelle);
    List<TransactionDAO> findAll();
    List<TransactionDAO> findAllPerPeriod(LocalDate dateDebut, LocalDate dateFin);
    List<TransactionDAO> findAllByClientId(Integer id);
    List<TransactionDAO> findAllByClientIdBetweenDate(LocalDate dateDebut, LocalDate dateFin,Integer id);
}
