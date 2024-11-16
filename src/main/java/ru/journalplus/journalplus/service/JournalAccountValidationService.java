package ru.journalplus.journalplus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.journalplus.journalplus.api.JournalAccountValidationApiClient;
import ru.journalplus.journalplus.dto.LoginJournalRequest;


@Service
@RequiredArgsConstructor
public class JournalAccountValidationService {

    private final JournalAccountValidationApiClient apiClient;

    public boolean validateJournalAccount(String username, String password) {
        LoginJournalRequest loginRequest = new LoginJournalRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        return apiClient.validateJournalAccount(loginRequest);
    }
}