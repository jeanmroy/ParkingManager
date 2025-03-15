package com.jmroy.api.parkingmanager.exception.vehicule;

import com.jmroy.api.parkingmanager.domain.location.Location;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidVehiculeLocationDoesNotMatchException extends ResponseStatusException {

    public InvalidVehiculeLocationDoesNotMatchException(Location location) {
        super(HttpStatus.BAD_REQUEST, "Vehicle location does not match expected location: " + location.getId());
    }
}