package ru.journalplus.journalplus.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.journalplus.journalplus.dto.LoginJournalRequest;
import ru.journalplus.journalplus.model.User;
import ru.journalplus.journalplus.service.UserRegistrationService;

import java.util.Map;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class UserRegistrationController {
    private final UserRegistrationService registrationService;

    @PostMapping("/journal")
    public ResponseEntity<User> registerWithJournalAccount(@RequestBody @Valid LoginJournalRequest loginJournalRequest) {
        User user = registrationService.registerUserWithJournalAccount(loginJournalRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/messenger")
    public ResponseEntity<String> linkMessengerAccount(@RequestBody Map<String, Object> payload) {
        Long userId = Long.valueOf(payload.get("userId").toString());
        Long messengerId = Long.valueOf(payload.get("messengerId").toString());

        registrationService.linkMessengerAccount(userId, messengerId);
        return ResponseEntity.ok("Messenger account linked successfully.");
    }
}
