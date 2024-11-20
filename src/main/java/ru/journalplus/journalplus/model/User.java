package ru.journalplus.journalplus.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
@Table(name = "user_account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "journal_account", referencedColumnName = "id")
    private UserJournalAccount journalAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "messenger_account", referencedColumnName = "id")
    private UserMessengerAccount messengerAccount;
}
