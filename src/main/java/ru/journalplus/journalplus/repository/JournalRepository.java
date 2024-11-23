package ru.journalplus.journalplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.journalplus.journalplus.model.UserJournalAccount;

public interface JournalRepository extends JpaRepository<UserJournalAccount, Long> {
    UserJournalAccount findByUsername(String username);
}