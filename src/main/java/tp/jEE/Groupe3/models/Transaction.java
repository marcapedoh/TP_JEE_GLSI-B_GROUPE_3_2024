package tp.jEE.Groupe3.models;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Transaction{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @CreatedDate
    @Column(name ="dateCreation",nullable = false,updatable = false)
    private Instant dateCreation;
    @Column(name = "montant")
    private double montant;
    @Column(name = "libelleTrans")
    private String libelleTran;
    @Column(name = "typeTransaction")
    @Enumerated(EnumType.STRING)
    private TypeTransaction typeTransaction;
    @ManyToOne
    @JoinColumn(name = "idCompte")
    private Compte compte;


}
