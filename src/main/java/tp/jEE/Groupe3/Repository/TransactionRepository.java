package tp.jEE.Groupe3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tp.jEE.Groupe3.DAO.TransactionDAO;
import tp.jEE.Groupe3.models.Compte;
import tp.jEE.Groupe3.models.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Optional<Transaction> findTransactionByLibelleTran(String libelle);
    @Query("select t from Transaction t where t.dateCreation >= :date1 and t.dateCreation <= :date2")
    List<Transaction> findAllBetweenDate(@Param("date1") LocalDate date1,@Param("date2") LocalDate date2);

    @Query("select t from Transaction t where t.compte.client.id= :id")
    List<Transaction> findAllByClientId(@Param("id") Integer id);

    @Query("select t from Transaction t where t.compte.client.id= :id and t.dateCreation >= :date1 and t.dateCreation <= :date2")
    List<Transaction> findAllByClientIdBetweenDate(@Param("date1") LocalDate date1,@Param("date2") LocalDate date2, @Param("id") Integer id);
}
