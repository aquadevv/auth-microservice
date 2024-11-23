package ru.journalplus.journalplus.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.journalplus.journalplus.IntegrationTestBase;
import ru.journalplus.journalplus.model.UserJournalAccount;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
class JournalRepositoryTests extends IntegrationTestBase {

    @Autowired
    private JournalRepository journalRepository;

    private UserJournalAccount userJournalAccount;

    @BeforeEach
    public void setUp() {
        userJournalAccount = new UserJournalAccount();
        userJournalAccount.setUsername("DimaShagahod");
        userJournalAccount.setPassword("SVOZOV");
        userJournalAccount.setValid(true);
    }

    @Test
    void testSaverJournalAccount() {
        UserJournalAccount savedJournalAccount = journalRepository.save(userJournalAccount);
        assertNotNull(savedJournalAccount.getId());
        assert savedJournalAccount.getUsername().equals(userJournalAccount.getUsername());
    }

    @Test
    void testDeleteJournalAccount() {
        UserJournalAccount savedJournalAccount = journalRepository.save(userJournalAccount);
        journalRepository.delete(savedJournalAccount);
        UserJournalAccount deletedJournalAccount = journalRepository.findById(savedJournalAccount.getId()).orElse(null);
        assertNull(deletedJournalAccount);
    }

    @Test
    void testUpdateJournalAccount() {
        UserJournalAccount savedJournalAccount = journalRepository.save(userJournalAccount);
        savedJournalAccount.setUsername("Naminal");
        journalRepository.save(savedJournalAccount);
        Assertions.assertEquals("Naminal", savedJournalAccount.getUsername());

    }

}
