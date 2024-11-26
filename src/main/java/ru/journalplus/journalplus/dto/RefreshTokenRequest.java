package ru.journalplus.journalplus.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Schema(description = "Запрос на обновление токена")
public class RefreshTokenRequest {
    private String refreshToken;
}
