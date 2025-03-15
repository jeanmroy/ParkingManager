package com.jmroy.api.parkingmanager.exception.vehicule;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jmroy.api.parkingmanager.exception.ResourceNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VehiculeNotFoundException extends ResourceNotFoundException {
    public VehiculeNotFoundException(Long id) {
        super(String.format("Vehicule with id %d not found.", id));
    }
}
