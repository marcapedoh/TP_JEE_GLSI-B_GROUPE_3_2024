package tp.jEE.Groupe3.models;


import jakarta.persistence.*;
import lombok.*;
import org.iban4j.Iban;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
public class Compte extends AbstractEntity{
    @Column(name = "numeroCompte")
    private String numeroCpt;
    @Column(name = "type_Compte")
    @Enumerated(EnumType.STRING)
    private TypeCompte typeCompte;
    @Column(name = "solde")
    private double solde;

    @Column(name = "codePIN",nullable = false)
    private String codePin;
    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;
    @OneToMany(mappedBy = "compte")
    private List<Transaction> transactionList;

}
