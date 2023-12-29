package tp.jEE.Groupe3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Groupe3Application {
	public static void main(String[] args) {
		SpringApplication.run(Groupe3Application.class, args);
	}
}
