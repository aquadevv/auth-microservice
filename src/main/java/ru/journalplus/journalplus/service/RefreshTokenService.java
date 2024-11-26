package ru.journalplus.journalplus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.journalplus.journalplus.model.RefreshToken;
import ru.journalplus.journalplus.model.User;
import ru.journalplus.journalplus.repository.RefreshTokenRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void updateRefreshToken(RefreshToken oldToken, String newToken) {
        oldToken.setToken(newToken);
        oldToken.setExpiryDate(LocalDateTime.now().plusWeeks(1));
        refreshTokenRepository.save(oldToken);
    }

    /**
     * Проверка валидности токена
     *
     * @param token refresh токен
     * @return токен, если он валиден
     */
    public RefreshToken validateRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .filter(refreshToken -> !refreshToken.getExpiryDate().isBefore(LocalDateTime.now()))
                .orElseThrow(() -> new RuntimeException("Refresh token is invalid or expired"));
    }

    /**
     * Удаление всех токенов пользователя
     *
     * @param user пользователь
     */
    public void deleteByUser(User user) {
        refreshTokenRepository.deleteByUser(user);
    }
}
