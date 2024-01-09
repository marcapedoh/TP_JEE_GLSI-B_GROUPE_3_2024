package tp.jEE.Groupe3.Auth;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tp.jEE.Groupe3.Config.JwtService;
import tp.jEE.Groupe3.Exception.EntityNotFoundException;
import tp.jEE.Groupe3.Repository.ClientRepository;
import tp.jEE.Groupe3.models.Client;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UserDetails user=clientRepository.findClientByUsername(request.getUsername()).orElseThrow(()-> new EntityNotFoundException("aucun utilisateur n'est trouvé!"));
        String jwtToken="";
        if(passwordEncoder.matches(request.getMotDePasse(),user.getPassword())){
            jwtToken=jwtService.generateToken(user);
        }
        UserDetails user1=clientRepository.findClientByUsername(request.getUsername()).orElseThrow(()-> new EntityNotFoundException("aucun utilisateur n'est trouvé!"));
        if(!StringUtils.hasLength(user1.getUsername())){
            log.warn("le username de ce utilisateur est nul");
        }

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse register(RegisterRequest request) {
        var user= Client.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .dateNais(request.getDateNais())
                .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                .username(request.getUsername())
                .numeroTel(request.getNumeroTel())
                .couriel(request.getCouriel())
                .sexe(request.getSexe())
                .adresse(request.getAdresse())
                .nationalite(request.getNationalite())
                .build();
        clientRepository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}