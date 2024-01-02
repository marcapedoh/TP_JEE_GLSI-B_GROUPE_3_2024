package tp.jEE.Groupe3.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tp.jEE.Groupe3.Repository.ClientRepository;
import tp.jEE.Groupe3.models.Client;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final ClientRepository clientRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> clientRepository.findClientByUsername(username).orElseThrow(()->
                new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider aUthenticationProvider(){
        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
