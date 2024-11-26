package ru.journalplus.journalplus.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
@Getter
public class UserAlreadyExistsException extends RuntimeException {
    private final String status;

    public UserAlreadyExistsException(String message) {
        super(message);
        this.status = "USER_ALREADY_EXISTS";
    }
}
