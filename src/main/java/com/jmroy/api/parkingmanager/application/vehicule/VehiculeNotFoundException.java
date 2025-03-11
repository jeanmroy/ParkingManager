package com.jmroy.api.parkingmanager.application.vehicule;

import com.jmroy.api.parkingmanager.application.shared.EntityNotFoundException;

public class VehiculeNotFoundException extends EntityNotFoundException {
    public VehiculeNotFoundException(Long id) {
        super(String.format("Vehicule with id %d not found.", id));
    }
}
