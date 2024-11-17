package ru.journalplus.journalplus.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.journalplus.journalplus.dto.LoginJournalRequest;

@Component
@RequiredArgsConstructor
public class JournalAccountValidationApiClientImpl implements JournalAccountValidationApiClient {

    private final RestTemplate restTemplate;

    @Override
    public boolean validateJournalAccount(LoginJournalRequest loginRequest) {
        String url = "https://msapi.top-academy.ru/api/v2/auth/login";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Referer", "https://journal.top-academy.ru/");

        HttpEntity<LoginJournalRequest> request = new HttpEntity<>(loginRequest, httpHeaders);
        try {
            ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}