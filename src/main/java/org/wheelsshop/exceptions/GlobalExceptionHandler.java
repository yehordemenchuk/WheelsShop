package org.wheelsshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ErrorDetails getErrorDetails(Exception e, WebRequest webRequest, HttpStatus httpStatus) {
        return new ErrorDetails(LocalDateTime.now(),
                e.getMessage(),
                webRequest.getDescription(false),
                httpStatus.name());
    }

    private ResponseEntity<ErrorDetails> handleException(Exception e, WebRequest webRequest, HttpStatus status) {
        return new ResponseEntity<>(getErrorDetails(e, webRequest, status), status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEntityNotFoundException(EntityNotFoundException e,
                                                                WebRequest webRequest) {
        return handleException(e, webRequest, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            NoSuchMethodException.class,
            InvocationTargetException.class,
            IllegalAccessException.class
    })
    public ResponseEntity<ErrorDetails> handleClassCastExceptions(Exception e, WebRequest webRequest) {
        return handleException(e, webRequest, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleBadCredentialsException(BadCredentialsException e,
                                                                      WebRequest webRequest) {
        return handleException(e, webRequest, HttpStatus.UNAUTHORIZED);
    }
}
