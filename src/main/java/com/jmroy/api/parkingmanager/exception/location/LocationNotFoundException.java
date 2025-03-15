package com.jmroy.api.parkingmanager.exception.location;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jmroy.api.parkingmanager.exception.ResourceNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LocationNotFoundException extends ResourceNotFoundException {

    public LocationNotFoundException(Long id) {
        super(String.format("Location with id %d not found.", id));
    }
}
