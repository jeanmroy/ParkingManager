package com.jmroy.api.parkingmanager.api.location;

import com.jmroy.api.parkingmanager.api.vehicule.VehiculeResource;
import com.jmroy.api.parkingmanager.domain.location.LocationName;
import com.jmroy.api.parkingmanager.domain.vehicule.VehiculeType;
import lombok.Data;

import java.util.Set;

@Data
public class LocationResource {
    private Long id;
    private int capacity;
    private LocationName locationName;
    private Set<VehiculeType> allowedVehiculeTypes;
    private Set<VehiculeResource> vehicules;
}
