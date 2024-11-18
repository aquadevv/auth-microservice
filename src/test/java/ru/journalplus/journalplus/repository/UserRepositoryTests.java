package ru.journalplus.journalplus.repository;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.journalplus.journalplus.config.TestConfig;
import ru.journalplus.journalplus.model.User;
import ru.journalplus.journalplus.model.UserJournalAccount;
import ru.journalplus.journalplus.model.UserMessengerAccount;

@SpringBootTest(classes = TestConfig.class)
@Transactional
public class UserRepositoryTests {

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

        assert savedUser.getId() != null;
        assert savedUser.getJournalAccount() != null;
        assert savedUser.getMessengerAccount() != null;

        assert savedUser.getMessengerAccount().getUserMessengerId() == 123456;
        assert savedUser.getJournalAccount().getUsername().equals("journal");
        assert savedUser.getJournalAccount().getPassword().equals("password");

        User loadedUser = userRepository.findById(savedUser.getId()).orElseThrow();
        assert loadedUser.getJournalAccount().getUsername().equals("journal");
        assert loadedUser.getMessengerAccount().getUserMessengerId() == 123456;
    }

    @Test
    void testCascadePersist() {
        UserJournalAccount journalAccount = new UserJournalAccount();
        journalAccount.setUsername("journal");

        User user = new User();

        User savedUser = userRepository.save(user);
        assert savedUser.getId() != null;
    }

    @Test
    void testCascadeDelete() {
        UserJournalAccount journalAccount = new UserJournalAccount();
        journalAccount.setUsername("journal");

        UserMessengerAccount messengerAccount = new UserMessengerAccount();
        messengerAccount.setUserMessengerId(123456L);

        User user = new User();
        user.setJournalAccount(journalAccount);
        user.setMessengerAccount(messengerAccount);

        User savedUser = userRepository.save(user);

        userRepository.delete(savedUser);

        assert userRepository.findById(savedUser.getId()).isEmpty();
    }
}
