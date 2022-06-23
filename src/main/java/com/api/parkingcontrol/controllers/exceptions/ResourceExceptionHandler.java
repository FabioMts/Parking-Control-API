package com.api.parkingcontrol.controllers.exceptions;


import com.api.parkingcontrol.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> resourceNotFound(BusinessException e, HttpServletRequest request) {


        StandardError err = new StandardError(LocalDateTime.now(ZoneId.of("UTC")), e.getStatusCode().value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(e.getStatusCode().value()).body(err);
    }


}
