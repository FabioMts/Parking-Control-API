package com.api.parkingcontrol.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class ResourceNotFoundException extends BusinessException{

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(UUID id) {
        super("Resource not found. Id: " + id, HttpStatus.NOT_FOUND);
    }

}
