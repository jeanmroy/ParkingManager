package com.jmroy.api.parkingmanager.exception.vehicule;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidEqYearException extends ResponseStatusException {

    public InvalidEqYearException(int eqYear) {
        super(HttpStatus.BAD_REQUEST, "Invalid equipment year: " + eqYear);
    }
}