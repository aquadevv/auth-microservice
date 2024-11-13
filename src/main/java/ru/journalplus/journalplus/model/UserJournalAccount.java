package ru.journalplus.journalplus.model;

import jakarta.persistence.*;


@Entity
@Table(name = "user_journal_account")
public class UserJournalAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
}
