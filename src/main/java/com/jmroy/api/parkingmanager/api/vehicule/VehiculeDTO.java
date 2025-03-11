package com.jmroy.api.parkingmanager.api.vehicule;

import com.jmroy.api.parkingmanager.domain.vehicule.VehiculeType;
import lombok.Data;

@Data
public class VehiculeDTO {
    private Long id;
    private String brand;
    private String model;
    private String note;
    private int eqYear;
    private String color;
    private String licencePlate;
    private VehiculeType type;
    private Long ownerId;
    private Long locationId;
}