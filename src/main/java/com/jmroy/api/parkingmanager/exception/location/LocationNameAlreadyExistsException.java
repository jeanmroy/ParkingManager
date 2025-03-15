package com.jmroy.api.parkingmanager.exception.location;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jmroy.api.parkingmanager.exception.BusinessLogicException;

@ResponseStatus(HttpStatus.CONFLICT)
public class LocationNameAlreadyExistsException extends BusinessLogicException {

    public LocationNameAlreadyExistsException(String locationName) {
        super(String.format("Location name %s already exists.", locationName));
    }
}