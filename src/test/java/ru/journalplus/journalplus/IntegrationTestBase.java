package ru.journalplus.journalplus;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.journalplus.journalplus.initializer.Postgres;

@SpringBootTest
@ContextConfiguration(initializers = {Postgres.Initializer.class})
@Transactional
public abstract class IntegrationTestBase {

    @BeforeAll
    static void init() {
        Postgres.container.start();
    }
}