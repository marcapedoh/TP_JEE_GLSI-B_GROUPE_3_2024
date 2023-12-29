package tp.jEE.Groupe3.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iban4j.Iban;
import tp.jEE.Groupe3.models.Client;
import tp.jEE.Groupe3.models.Compte;
import tp.jEE.Groupe3.models.Transaction;
import tp.jEE.Groupe3.models.TypeCompte;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CompteDAO {
    private Integer id;
    private Iban numeroCpt;
    private TypeCompte typeCompte;
    private BigDecimal solde;
    private ClientDAO client;
    @JsonIgnore
    private List<Transaction> transactionList;
    public static CompteDAO fromEntity(Compte compte){
        if(compte==null){
            return null;
        }
        return CompteDAO.builder()
                .numeroCpt(compte.getNumeroCpt())
                .typeCompte(compte.getTypeCompte())
                .solde(compte.getSolde())
                .client(ClientDAO.fromEntity(compte.getClient()))
                .build();
    }

    public static Compte toEntity(CompteDAO compteDAO){
        if(compteDAO==null){
            return null;
        }
        Compte compte=new Compte();
        compte.setId(compteDAO.getId());
        compte.setNumeroCpt(compteDAO.getNumeroCpt());
        compte.setTypeCompte(compteDAO.getTypeCompte());
        compte.setSolde(compteDAO.getSolde());
        compte.setClient(ClientDAO.toEntity(compteDAO.getClient()));
        return compte;
    }
}

