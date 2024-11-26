package ru.journalplus.journalplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.journalplus.journalplus.model.RefreshToken;
import ru.journalplus.journalplus.model.User;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user);
}
