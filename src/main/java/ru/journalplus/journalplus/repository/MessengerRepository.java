package ru.journalplus.journalplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.journalplus.journalplus.model.UserMessengerAccount;

public interface MessengerRepository extends JpaRepository<UserMessengerAccount, Long> {
}
