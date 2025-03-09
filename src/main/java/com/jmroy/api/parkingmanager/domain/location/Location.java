package com.jmroy.api.parkingmanager.domain.location;

import java.util.Set;

import com.jmroy.api.parkingmanager.domain.shared.BaseEntity;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
import com.jmroy.api.parkingmanager.domain.vehicule.VehiculeType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Location extends BaseEntity {

    private int capacity;

    @Enumerated(EnumType.ORDINAL)
    private LocationName locationName;

    @OneToMany(mappedBy = "location")
    private Set<Vehicule> vehicules;

    private Set<VehiculeType> allowedVehiculeTypes;

    public boolean isAvailable() {
        return vehicules.size() < capacity;
    }
}
