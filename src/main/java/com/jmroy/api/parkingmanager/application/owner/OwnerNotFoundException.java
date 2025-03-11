package com.jmroy.api.parkingmanager.application.owner;

import com.jmroy.api.parkingmanager.application.shared.EntityNotFoundException;

public class OwnerNotFoundException extends EntityNotFoundException {
    public OwnerNotFoundException(Long id) {
        super(String.format("Owner with id %d not found.", id));
    }
}
