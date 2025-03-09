package com.jmroy.api.parkingmanager.domain.vehicule;

import com.jmroy.api.parkingmanager.domain.location.Location;
import com.jmroy.api.parkingmanager.domain.owner.Owner;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Vehicule extends BaseEquipment {

    private String brand;
    private String model;
    private String note;
    private int eqYear;

    @Enumerated(EnumType.ORDINAL)
    private VehiculeType type;

    @ManyToOne
    private Location location;

}
