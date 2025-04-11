package br.com.fiap.Bloom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "API Bloom", version = "v1", description = "API de sistem de Blog para as atividades da materia de Java", contact = @Contact(name = "Mariana Christina e Gabriela Moguinho", email = "rm554773@fiap.com.br")))
public class BloomApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloomApplication.class, args);
	}

}
