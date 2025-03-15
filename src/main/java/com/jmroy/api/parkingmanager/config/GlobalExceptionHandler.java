package com.jmroy.api.parkingmanager.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jmroy.api.parkingmanager.exception.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ MethodArgumentNotValidException.class, ValidationException.class })
    public ResponseEntity<Map<String, List<String>>> handleValidationExceptions(Exception ex) {
        List<String> errors = extractErrors(ex);

        if (errors == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    private List<String> extractErrors(Exception ex) {
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) ex;
            return manve.getBindingResult().getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
        } else if (ex instanceof ValidationException) {
            ValidationException ve = (ValidationException) ex;
            return ve.getBindingResult().getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
        }
        return null;
    }
}