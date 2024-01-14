package tp.jEE.Groupe3.DAO;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import tp.jEE.Groupe3.models.Client;
import tp.jEE.Groupe3.models.Compte;
import tp.jEE.Groupe3.models.Transaction;
import tp.jEE.Groupe3.models.TypeTransaction;

import java.math.BigDecimal;
import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDAO {
    private Integer id;
    private Instant dateCreation;
    private double montant;
    private String libelleTran;
    private TypeTransaction typeTransaction;
    private CompteDAO compte;

    public static TransactionDAO fromEntity(Transaction transaction){
        if(transaction==null){
            return null;
        }
        return TransactionDAO.builder()
                .id(transaction.getId())
                .dateCreation(transaction.getDateCreation())
                .montant(transaction.getMontant())
                .typeTransaction(transaction.getTypeTransaction())
                .libelleTran(transaction.getLibelleTran())
                .compte(CompteDAO.fromEntity(transaction.getCompte()))
                .build();
    }

    public static Transaction toEntity(TransactionDAO transactionDAO){
        if(transactionDAO==null){
            return null;
        }
        return Transaction.builder()
                .id(transactionDAO.getId())
                .dateCreation(transactionDAO.getDateCreation())
                .montant(transactionDAO.getMontant())
                .typeTransaction(transactionDAO.getTypeTransaction())
                .libelleTran(transactionDAO.getLibelleTran())
                .compte(CompteDAO.toEntity(transactionDAO.getCompte()))
                .build();
    }
}
