package ru.journalplus.journalplus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginJournalRequest {
    @JsonProperty("application_key")
    @Setter(AccessLevel.NONE)
    private String applicationKey = "6a56a5df2667e65aab73ce76d1dd737f7d1faef9c52e8b8c55ac75f565d8e8a6";

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String username;

    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;

    @JsonProperty("id_city")
    @Setter(AccessLevel.NONE)
    private String idCity = null;
}
