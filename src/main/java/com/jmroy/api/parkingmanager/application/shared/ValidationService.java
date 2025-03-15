package com.jmroy.api.parkingmanager.application.shared;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jmroy.api.parkingmanager.exception.ValidationException;

@Service
public class ValidationService {

    public void validateForm(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
    }
}