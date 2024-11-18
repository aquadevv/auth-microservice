package ru.journalplus.journalplus;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    protected static Dotenv dotenv;

    @BeforeAll
    static void setUp() {
        dotenv = Dotenv.load();
        for (DotenvEntry entry : dotenv.entries()) {
            System.setProperty(entry.getKey(), entry.getValue());
        }
    }
}