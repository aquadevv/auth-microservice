package ru.journalplus.journalplus.model;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "journal_account", referencedColumnName = "id")
    private UserJournalAccount journalAccount;
}
