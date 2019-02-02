package pl.project.saveKid;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import pl.project.saveKid.config.FirebaseConfiguration;
import pl.project.saveKid.entity.User;
import pl.project.saveKid.repository.UserRepository;

@SpringBootApplication
@Import({FirebaseConfiguration.class})
public class SaveKidApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SaveKidApplication.class, args);
	}


	@Bean
    public CommandLineRunner init(UserRepository userRepository){

	    return(args) ->{
            userRepository.save(new User("17:252:187:121",false));
            userRepository.save(new User("0:48:234:162",false));

            System.out.println("users initialized");
        };

    }
}

