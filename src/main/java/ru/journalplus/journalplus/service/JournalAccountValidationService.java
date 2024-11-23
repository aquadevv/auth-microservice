package ru.journalplus.journalplus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.journalplus.journalplus.api.JournalAccountValidationApiClient;
import ru.journalplus.journalplus.dto.LoginJournalRequest;


@Service
@RequiredArgsConstructor
public class JournalAccountValidationService {

    private final JournalAccountValidationApiClient apiClient;

    public boolean validateJournalAccount(LoginJournalRequest loginJournalRequest) {
        return apiClient.validateJournalAccount(loginJournalRequest);
    }
}