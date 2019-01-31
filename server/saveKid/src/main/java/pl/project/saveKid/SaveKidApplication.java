package pl.project.saveKid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.project.saveKid.config.FirebaseConfiguration;

@SpringBootApplication
@EnableAutoConfiguration
@Import({FirebaseConfiguration.class})
public class SaveKidApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SaveKidApplication.class, args);
	}

}

