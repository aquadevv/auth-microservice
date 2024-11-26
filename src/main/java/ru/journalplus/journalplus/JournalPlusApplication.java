package ru.journalplus.journalplus;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
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
        loadEnvironmentVariables();
        SpringApplication.run(JournalPlusApplication.class, args);
    }

    private static void loadEnvironmentVariables() {
        Dotenv dotenv = null;

        try {
            dotenv = Dotenv.load();
        } catch (Exception e) {
            log.warn(".env file not found, proceeding without it.");
        }

        if (dotenv != null) {
            for (DotenvEntry entry : dotenv.entries()) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        }
    }
}
