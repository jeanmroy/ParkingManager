package com.jmroy.api.parkingmanager.exception;

import org.springframework.validation.BindingResult;

/**
 * 
 * {@code ValidationException} is specifically designed to encapsulate
 * validation errors from {@code BindingResult}.
 * It is used both in the {@code ValidationService} (service logic) and handled
 * by the {@code @ControllerAdvice} (exception handling).
 * Therefore, it acts as a bridge between validation logic and error management.
 */
public class ValidationException extends RuntimeException {

    private final BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult) {
        super("Erreurs de validation");
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}