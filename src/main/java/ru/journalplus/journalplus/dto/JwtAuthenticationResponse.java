package ru.journalplus.journalplus.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "Ответ c токеном доступа и токеном обновления")
public class JwtAuthenticationResponse {
    @Schema(description = "Токен доступа", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyMjUwNj...")
    private String accessToken;

    @Schema(description = "Токен обновления", example = "d3d2d49d3b5f674b1a4e17b89893c5f31b4b6b39d87b2c54142a77fc...")
    private String refreshToken;
}