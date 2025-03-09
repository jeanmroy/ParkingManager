package com.jmroy.api.parkingmanager.application.vehicule;

import com.jmroy.api.parkingmanager.domain.vehicule.VehiculeType;
import lombok.Data;

@Data
public class VehiculeDTO {
    private String brand;
    private String model;
    private String note;
    private VehiculeType type;
    private int year;
    private String color;
    private String licencePlate;
}
