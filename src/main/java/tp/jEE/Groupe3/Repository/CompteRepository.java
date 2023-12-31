package tp.jEE.Groupe3.Repository;

import org.iban4j.Iban;
import org.springframework.data.jpa.repository.JpaRepository;
import tp.jEE.Groupe3.models.Client;
import tp.jEE.Groupe3.models.Compte;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte,Integer> {
    Optional<Compte> findCompteByNumeroCpt(Iban iban);
    Client findCompteByClientId(Integer id);
}
