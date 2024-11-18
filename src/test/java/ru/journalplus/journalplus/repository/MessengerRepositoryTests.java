package ru.journalplus.journalplus.repository;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.journalplus.journalplus.config.TestConfig;
import ru.journalplus.journalplus.model.UserMessengerAccount;

import java.util.Optional;

@SpringBootTest(classes = TestConfig.class)
@Transactional
public class MessengerRepositoryTests {

    @Autowired
    private MessengerRepository messengerRepository;


    @Test
    void testFindById() {

        UserMessengerAccount account = new UserMessengerAccount();
        account.setUserMessengerId(123456L);
        messengerRepository.save(account);

        UserMessengerAccount foundAccount = messengerRepository.findById(account.getId()).orElseThrow();

        assert foundAccount.getUserMessengerId() == 123456L;

    }

    @Test
    void testDelete() {
        UserMessengerAccount account = new UserMessengerAccount();
        account.setUserMessengerId(123456L);
        UserMessengerAccount savedAccount = messengerRepository.save(account);

        messengerRepository.deleteById(savedAccount.getId());

        Optional<UserMessengerAccount> deletedAccount = messengerRepository.findById(savedAccount.getId());

        assert deletedAccount.isEmpty();
    }
}
