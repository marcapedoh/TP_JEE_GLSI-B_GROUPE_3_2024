package tp.jEE.Groupe3.Repository;

import org.iban4j.Iban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tp.jEE.Groupe3.models.Client;
import tp.jEE.Groupe3.models.Compte;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    Optional<Client> findClientByUsername(String username);
    Optional<Client> findClientByNom(String nom);
    @Query("select cpt from  Compte cpt where cpt.client.id= :idClient")
    List<Compte> findAllCompte(@Param("idClient") Integer id);
    @Query("select clt from Compte c, Client clt where c.id=clt.id and c.numeroCpt= :iban")
    Client findClientWhoHasACompt(@Param("iban")Iban iban);
}
