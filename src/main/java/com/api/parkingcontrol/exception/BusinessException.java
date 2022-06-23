package com.api.parkingcontrol.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException{

    private HttpStatus statusCode;


    public BusinessException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
