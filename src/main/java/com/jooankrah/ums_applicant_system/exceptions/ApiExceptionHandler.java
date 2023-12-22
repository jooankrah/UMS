package com.jooankrah.ums_applicant_system.exceptions;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<Object> handleAuthException(@NotNull BadCredentialsException exception) {
        ApiException apiException = new ApiException(
                exception.getMessage(),
                HttpStatus.UNAUTHORIZED,
                ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = ApiRequestException.class)
    public ResponseEntity<Object> handleApiRequestException(@NotNull ApiRequestException exception) {
        ApiException apiException = new ApiException(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleANotFoundRequestException(@NotNull NotFoundException exception) {
        ApiException apiException = new ApiException(
                exception.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(@NotNull MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // @ExceptionHandler(value = RuntimeException.class)
    // public ResponseEntity<Object> handleApiRequestException(@NotNull
    // RuntimeException exception) {
    // ApiException apiException = new ApiException(
    // exception.getMessage(),
    // HttpStatus.FORBIDDEN,
    // ZonedDateTime.now());
    // return new ResponseEntity<>(apiException, HttpStatus.FORBIDDEN);
    // }
}
