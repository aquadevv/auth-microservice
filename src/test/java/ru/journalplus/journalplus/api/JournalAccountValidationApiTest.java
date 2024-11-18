package ru.journalplus.journalplus.api;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import ru.journalplus.journalplus.dto.LoginJournalRequest;


class JournalAccountValidationApiTest {

    private JournalAccountValidationApiClientImpl apiClient;

    @BeforeEach
    void setUp() {
        RestTemplate restTemplate = new RestTemplate();
        apiClient = new JournalAccountValidationApiClientImpl(restTemplate);
    }

    @Test
    void validateJournalAccount() {
        String username = System.getenv("JOURNAL_USERNAME");
        String password = System.getenv("JOURNAL_PASSWORD");

        LoginJournalRequest loginRequest = new LoginJournalRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        boolean isValid = apiClient.validateJournalAccount(loginRequest);

        assert isValid;
    }
}
