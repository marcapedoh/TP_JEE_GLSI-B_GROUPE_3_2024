package tp.jEE.Groupe3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.jEE.Groupe3.models.Compte;
import tp.jEE.Groupe3.models.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Optional<Transaction> findTransactionByLibelleTran(String libelle);
}
