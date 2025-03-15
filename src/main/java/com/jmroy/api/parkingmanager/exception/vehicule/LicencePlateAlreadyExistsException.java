package com.jmroy.api.parkingmanager.exception.vehicule;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LicencePlateAlreadyExistsException extends ResponseStatusException {

    public LicencePlateAlreadyExistsException(String licencePlate) {
        super(HttpStatus.CONFLICT, "Licence plate '" + licencePlate + "' already exists.");
    }
}