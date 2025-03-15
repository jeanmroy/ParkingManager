package com.jmroy.api.parkingmanager.exception.owner;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jmroy.api.parkingmanager.exception.ResourceNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OwnerNotFoundException extends ResourceNotFoundException {
    public OwnerNotFoundException(Long id) {
        super(String.format("Owner with id %d not found.", id));
    }
}
