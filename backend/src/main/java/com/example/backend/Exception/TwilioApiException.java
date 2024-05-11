package com.example.backend.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.twilio.exception.ApiException;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class TwilioApiException extends ApiException {
    public TwilioApiException(String message) {
        super(message);
    }
}
