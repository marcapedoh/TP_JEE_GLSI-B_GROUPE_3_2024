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
    private TypeCompte typeCompte;
    private String numeroCpt;
    private double solde;
    private String codePin;
    private ClientDAO client;
    @JsonIgnore
    private List<Transaction> transactionList;



    public static CompteDAO fromEntity(Compte compte){
        if(compte==null){
            return null;
        }
        return CompteDAO.builder()
                .id(compte.getId())
                .typeCompte(compte.getTypeCompte())
                .numeroCpt(compte.getNumeroCpt())
                .solde(compte.getSolde())
                .codePin(compte.getCodePin())
                .client(ClientDAO.fromEntity(compte.getClient()))
                .build();
    }

    public static Compte toEntity(CompteDAO compteDAO){
        if(compteDAO==null){
            return null;
        }
        Compte compte=new Compte();
        compte.setId(compteDAO.getId());
        compte.setNumeroCpt(getNumeroCptDAO());
        compte.setTypeCompte(compteDAO.getTypeCompte());
        compte.setCodePin(compteDAO.getCodePin());
        compte.setSolde(compteDAO.getSolde());
        compte.setClient(ClientDAO.toEntity(compteDAO.getClient()));
        return compte;
    }

    public static String getNumeroCptDAO(){
        Iban iban = Iban.random();

        return iban.toString();
    }
}

