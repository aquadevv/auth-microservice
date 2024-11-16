package ru.journalplus.journalplus.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidationJournalResponse {
    private boolean success;

    public ValidationJournalResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
