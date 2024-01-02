package tp.jEE.Groupe3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.jEE.Groupe3.models.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    Optional<Client> findClientByUsername(String username);
}
