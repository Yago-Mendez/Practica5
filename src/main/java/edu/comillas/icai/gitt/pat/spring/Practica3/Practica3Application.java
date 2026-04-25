package edu.comillas.icai.gitt.pat.spring.Practica3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
    "edu.comillas.icai.gitt.pat.spring.Practica3",
    "entity", "repository", "service"
})
@EntityScan("entity")
@EnableJpaRepositories("repository")
public class Practica3Application {

	public static void main(String[] args) {
		SpringApplication.run(Practica3Application.class, args);
	}

}
//http://localhost:8080/productos.html