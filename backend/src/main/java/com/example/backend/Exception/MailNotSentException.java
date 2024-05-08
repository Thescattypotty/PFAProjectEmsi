package com.example.backend.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class MailNotSentException extends RuntimeException {
    public MailNotSentException(String message) {
        super(message);
    }
}
