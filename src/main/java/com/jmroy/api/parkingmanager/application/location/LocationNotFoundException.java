package com.jmroy.api.parkingmanager.application.location;

import com.jmroy.api.parkingmanager.application.shared.EntityNotFoundException;

public class LocationNotFoundException extends EntityNotFoundException {
    public LocationNotFoundException(Long id) {
        super(String.format("Location with id %d not found.", id));
    }
}
