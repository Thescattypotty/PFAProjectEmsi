package com.example.backend.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserNotSignedInException extends RuntimeException
{
    public UserNotSignedInException(String message)
    {
        super(message);
    }
}
