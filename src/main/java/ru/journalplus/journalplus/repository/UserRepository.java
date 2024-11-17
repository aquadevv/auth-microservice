package ru.journalplus.journalplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.journalplus.journalplus.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
