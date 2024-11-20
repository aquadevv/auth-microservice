package ru.journalplus.journalplus;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "${spring.application.name}",
                version = "1.0",
                description = "API Documentation for JournalPlus"
        )
)
public class JournalPlusApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        for (DotenvEntry entry : dotenv.entries()) {
            System.setProperty(entry.getKey(), entry.getValue());
        }

        SpringApplication.run(JournalPlusApplication.class, args);
    }
}
