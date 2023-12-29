package tp.jEE.Groupe3.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Client extends AbstractEntity{

    @Column(name = "nom",nullable = false)
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "dateNais",nullable = false)
    private LocalDate dateNais;
    @Column(name = "adresse",nullable = false)
    private String adresse;
    @Column(name = "sexeType",nullable = false)
    @Enumerated(EnumType.STRING)
    private SexeType sexe;
    @Column(name = "numeroTel",nullable = false)
    private String numeroTel;
    @Column(name = "couriel",nullable = false)
    private String couriel;
    @Column(name = "nationalite",nullable = false)
    private String nationalite;
    @Column(name = "username",nullable = false)
    private  String username;
    @Column(name = "motDePasse",nullable = false)
    private String motDePasse;

    @OneToMany(mappedBy = "client")
    private List<Compte> listCompte;
    @OneToMany(mappedBy = "client")
    private List<Transaction> transactionList;
}
