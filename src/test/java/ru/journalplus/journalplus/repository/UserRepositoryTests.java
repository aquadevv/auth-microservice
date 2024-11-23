package ru.journalplus.journalplus.repository;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.journalplus.journalplus.IntegrationTestBase;
import ru.journalplus.journalplus.model.User;
import ru.journalplus.journalplus.model.UserJournalAccount;
import ru.journalplus.journalplus.model.UserMessengerAccount;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTests extends IntegrationTestBase {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testUserEntityMapping() {
        UserJournalAccount journalAccount = new UserJournalAccount();
        journalAccount.setUsername("journal");
        journalAccount.setPassword("password");
        journalAccount.setValid(true);

        UserMessengerAccount messengerAccount = new UserMessengerAccount();
        messengerAccount.setUserMessengerId(123456L);

        User user = new User();
        user.setJournalAccount(journalAccount);
        user.setMessengerAccount(messengerAccount);

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getJournalAccount());
        assertNotNull(savedUser.getMessengerAccount());

        assertEquals(123456,  savedUser.getMessengerAccount().getUserMessengerId());
        assertEquals("journal",  savedUser.getJournalAccount().getUsername());
        assertEquals("password", savedUser.getJournalAccount().getPassword());

        User loadedUser = userRepository.findById(savedUser.getId()).orElseThrow();
        assertEquals("journal", loadedUser.getJournalAccount().getUsername());
        assertEquals(123456, loadedUser.getMessengerAccount().getUserMessengerId());
    }

    @Test
    void testCascadePersist() {
        UserJournalAccount journalAccount = new UserJournalAccount();
        journalAccount.setUsername("journal");
        journalAccount.setPassword("password");
        journalAccount.setValid(true);

        User user = new User();

        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());
    }

    @Test
    void testCascadeDelete() {
        UserJournalAccount journalAccount = new UserJournalAccount();
        journalAccount.setUsername("journal");
        journalAccount.setPassword("password");
        journalAccount.setValid(true);

        UserMessengerAccount messengerAccount = new UserMessengerAccount();
        messengerAccount.setUserMessengerId(123456L);

        User user = new User();
        user.setJournalAccount(journalAccount);
        user.setMessengerAccount(messengerAccount);

        User savedUser = userRepository.save(user);

        userRepository.delete(savedUser);

        assertTrue(userRepository.findById(savedUser.getId()).isEmpty());
    }
}
