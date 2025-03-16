package com.jmroy.api.parkingmanager.api.vehicule;

import com.jmroy.api.parkingmanager.api.location.LocationResource;
import com.jmroy.api.parkingmanager.api.owner.OwnerResource;
import com.jmroy.api.parkingmanager.domain.vehicule.VehiculeType;
import lombok.Data;

@Data
public class VehiculeResource {
    private Long id;
    private String brand;
    private String model;
    private String note;
    private int eqYear;
    private String color;
    private String licencePlate;
    private VehiculeType type;
    private OwnerResource owner;
    private LocationResource location;
}
