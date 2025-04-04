package org.wheelsshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<String> handleException(Exception e, HttpStatus status) {
        return new ResponseEntity<>(e.getMessage(), status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return handleException(e, HttpStatus.NOT_FOUND);
    }
}
