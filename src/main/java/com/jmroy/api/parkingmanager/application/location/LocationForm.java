package com.jmroy.api.parkingmanager.application.location;

import java.util.Set;

import jakarta.validation.constraints.NotNull;

import com.jmroy.api.parkingmanager.domain.location.LocationName;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
import com.jmroy.api.parkingmanager.domain.vehicule.VehiculeType;

import lombok.Data;

@Data
public class LocationForm {
    @NotNull
    private int capacity;
    @NotNull
    private LocationName locationName;
    @NotNull
    private Set<VehiculeType> allowedVehiculeTypes;
    private Set<Vehicule> vehicules;
}
