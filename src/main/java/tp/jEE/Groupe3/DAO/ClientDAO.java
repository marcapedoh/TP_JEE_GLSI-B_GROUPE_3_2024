package tp.jEE.Groupe3.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tp.jEE.Groupe3.models.Client;
import tp.jEE.Groupe3.models.Compte;
import tp.jEE.Groupe3.models.SexeType;
import tp.jEE.Groupe3.models.Transaction;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDAO {
    private Integer id;
    private String nom;
    private String prenom;
    private LocalDate dateNais;
    private String adresse;
    private SexeType sexe;
    private String numeroTel;
    private String couriel;
    private String nationalite;
    private  String username;
    private String motDePasse;

    @JsonIgnore
    private List<CompteDAO> listCompte;
    @JsonIgnore
    private List<TransactionDAO> transactionList;

    public static ClientDAO fromEntity(Client client){
        if (client==null){
            return null;
        }
        return ClientDAO.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .sexe(client.getSexe())
                .dateNais(client.getDateNais())
                .adresse(client.getAdresse())
                .numeroTel(client.getNumeroTel())
                .couriel(client.getCouriel())
                .nationalite(client.getNationalite())
                .username(client.getUsername())
                .motDePasse(client.getMotDePasse())
                .build();
    }
    public static Client toEntity(ClientDAO clientDAO){
        if (clientDAO==null) {
            return null;
        }
        Client client= new Client();
        client.setId(clientDAO.getId());
        client.setNom(clientDAO.getNom());
        client.setPrenom(clientDAO.getPrenom());
        client.setSexe(clientDAO.getSexe());
        client.setDateNais(clientDAO.getDateNais());
        client.setAdresse(clientDAO.getAdresse());
        client.setNumeroTel(clientDAO.getNumeroTel());
        client.setCouriel(clientDAO.getCouriel());
        client.setNationalite(clientDAO.getNationalite());
        client.setUsername(clientDAO.getUsername());
        client.setMotDePasse(clientDAO.getMotDePasse());
        return client;
    }
}
