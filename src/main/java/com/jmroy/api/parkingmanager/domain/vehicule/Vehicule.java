package com.jmroy.api.parkingmanager.domain.vehicule;

import com.jmroy.api.parkingmanager.domain.location.Location;
import com.jmroy.api.parkingmanager.domain.owner.Owner;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Vehicule extends BaseEquipment {

    private String brand;
    private String model;
    private String note;
    private int eqYear;

    @Enumerated(EnumType.ORDINAL)
    private VehiculeType type;

    @ManyToOne
    private Location location;

    public Vehicule(String brand, String model, String note, int eqYear, VehiculeType type, Location location,
            String color, String licencePlate, Owner owner) {
        this.brand = brand;
        this.model = model;
        this.note = note;
        this.eqYear = eqYear;
        this.type = type;
        this.location = location;
        this.color = color;
        this.licencePlate = licencePlate;
        this.owner = owner;
    }

}
