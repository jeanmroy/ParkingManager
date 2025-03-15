package com.jmroy.api.parkingmanager.exception.vehicule;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidVehiculeException extends RuntimeException {

    public InvalidVehiculeException() {
        super(String.format("Vehicule is invalid."));
    }

    public InvalidVehiculeException(String message) {
        super(message);
    }

    public InvalidVehiculeException(String message, Throwable cause) {
        super(message, cause);
    }
}