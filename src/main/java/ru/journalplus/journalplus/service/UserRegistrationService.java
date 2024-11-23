package ru.journalplus.journalplus.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.journalplus.journalplus.dto.LoginJournalRequest;
import ru.journalplus.journalplus.exception.UserAlreadyExistsException;
import ru.journalplus.journalplus.model.User;
import ru.journalplus.journalplus.model.UserJournalAccount;
import ru.journalplus.journalplus.model.UserMessengerAccount;
import ru.journalplus.journalplus.repository.JournalRepository;
import ru.journalplus.journalplus.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {
    private final UserRepository userRepository;
    private final JournalRepository journalRepository;
    private final JournalAccountValidationService journalAccountValidationService;

    @Transactional
    public User registerUserWithJournalAccount(LoginJournalRequest loginJournalRequest) {
        Optional<UserJournalAccount> existingUser = Optional.ofNullable(journalRepository.findByUsername(loginJournalRequest.getUsername()));
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("A user with this login already exists");
        }

        boolean isValid = journalAccountValidationService.validateJournalAccount(loginJournalRequest);
        if (!isValid) {
            throw new IllegalArgumentException("Log data is invalid");
        }

        UserJournalAccount journalAccount = new UserJournalAccount();
        journalAccount.setUsername(loginJournalRequest.getUsername());
        journalAccount.setPassword(loginJournalRequest.getPassword());
        journalAccount.setValid(true);

        User user = new User();
        user.setJournalAccount(journalAccount);

        return userRepository.save(user);
    }

    @Transactional
    public void linkMessengerAccount(Long userId, Long messengerId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        UserMessengerAccount messengerAccount = new UserMessengerAccount();
        messengerAccount.setUserMessengerId(messengerId);

        user.setMessengerAccount(messengerAccount);
        userRepository.save(user);
    }
}
