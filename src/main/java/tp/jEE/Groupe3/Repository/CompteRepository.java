package tp.jEE.Groupe3.Repository;

import org.iban4j.Iban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tp.jEE.Groupe3.models.Client;
import tp.jEE.Groupe3.models.Compte;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte,Integer> {
    @Query("select c from Compte c where c.numeroCpt= :iban")
    Optional<Compte> findCompteByNumeroCpt(@Param("iban") String iban);
    List<Compte> findCompteByClientId(Integer id);
}
