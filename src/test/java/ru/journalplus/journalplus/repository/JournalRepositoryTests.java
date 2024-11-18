package ru.journalplus.journalplus.repository;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.journalplus.journalplus.model.UserJournalAccount;

@SpringBootTest
@Transactional
public class JournalRepositoryTests {

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
        assert savedJournalAccount.getId() != null;
        assert savedJournalAccount.getUsername().equals(userJournalAccount.getUsername());
    }

    @Test
    void testDeleteJournalAccount() {
        UserJournalAccount savedJournalAccount = journalRepository.save(userJournalAccount);
        journalRepository.delete(savedJournalAccount);
        UserJournalAccount deletedJournalAccount = journalRepository.findById(savedJournalAccount.getId()).orElse(null);
        assert deletedJournalAccount == null;
    }

    @Test
    void testUpdateJournalAccount() {
        UserJournalAccount savedJournalAccount = journalRepository.save(userJournalAccount);
        savedJournalAccount.setUsername("Naminal");
        journalRepository.save(savedJournalAccount);
        assert savedJournalAccount.getUsername().equals("Naminal");

    }

}
