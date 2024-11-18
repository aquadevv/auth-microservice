package ru.journalplus.journalplus.config;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    private final Dotenv dotenv = Dotenv.load();

    public TestConfig() {
        init();
    }

    private void init() {
        for (DotenvEntry entry : dotenv.entries()) {
            System.setProperty(entry.getKey(), entry.getValue());
        }
    }
}