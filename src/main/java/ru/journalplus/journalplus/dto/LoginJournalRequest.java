package ru.journalplus.journalplus.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginJournalRequest {
    private String application_key = "6a56a5df2667e65aab73ce76d1dd737f7d1faef9c52e8b8c55ac75f565d8e8a6";
    private String username;
    private String password;
    private String id_city = null;
}
