package ru.journalplus.journalplus;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
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
            System.out.println(".env file not found, proceeding without it.");
        }

        if (dotenv != null) {
            for (DotenvEntry entry : dotenv.entries()) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        }
    }
}
