package tp.jEE.Groupe3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.jEE.Groupe3.models.Compte;

public interface CompteRepostory extends JpaRepository<Compte,Integer> {
}
