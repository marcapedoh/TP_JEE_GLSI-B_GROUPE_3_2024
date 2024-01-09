package tp.jEE.Groupe3.Auth;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tp.jEE.Groupe3.models.SexeType;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
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
}
