package com.jmroy.api.parkingmanager.api.vehicule;

import com.jmroy.api.parkingmanager.domain.location.Location;
import com.jmroy.api.parkingmanager.domain.owner.Owner;
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
    private Owner owner;
    private Location location;
}