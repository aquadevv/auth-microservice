package ru.journalplus.journalplus.api;

import ru.journalplus.journalplus.dto.LoginJournalRequest;

public interface JournalAccountValidationApiClient {
    boolean validateJournalAccount(LoginJournalRequest loginRequest);
}