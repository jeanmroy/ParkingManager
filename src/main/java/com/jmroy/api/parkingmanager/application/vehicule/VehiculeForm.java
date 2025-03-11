package com.jmroy.api.parkingmanager.application.vehicule;

import com.jmroy.api.parkingmanager.domain.location.Location;
import com.jmroy.api.parkingmanager.domain.owner.Owner;
import com.jmroy.api.parkingmanager.domain.vehicule.VehiculeType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VehiculeForm {

    private String brand;
    private String model;
    private String note;
    private int eqYear;
    @NotEmpty
    @Size(min = 1, max = 20)
    private String color;
    @NotEmpty
    @Size(min = 1, max = 10)
    private String licencePlate;
    @NotNull
    private VehiculeType type;
    @NotNull
    private Owner owner;
    private Location location;
}
