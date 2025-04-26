package org.wheelsshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.InvocationTargetException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<String> handleException(Exception e, HttpStatus status) {
        return new ResponseEntity<>(e.getMessage(), status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return handleException(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            NoSuchMethodException.class,
            InvocationTargetException.class,
            IllegalAccessException.class
    })
    public ResponseEntity<String> handleClassCastExceptions(Exception e) {
        return handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
        return handleException(e, HttpStatus.UNAUTHORIZED);
    }
}
