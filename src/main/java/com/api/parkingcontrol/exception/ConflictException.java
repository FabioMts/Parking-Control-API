package com.api.parkingcontrol.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class ConflictException extends BusinessException{

    private static final long serialVersionUID = 1L;

    public ConflictException(String message) {
        super("Conflict: " + message, HttpStatus.CONFLICT);
    }

}
