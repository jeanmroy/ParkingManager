package com.jmroy.api.parkingmanager.domain.location;

import java.util.Set;

import com.jmroy.api.parkingmanager.domain.shared.BaseEntity;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
import com.jmroy.api.parkingmanager.domain.vehicule.VehiculeType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Location extends BaseEntity {

    private int capacity;

    @Enumerated(EnumType.ORDINAL)
    private LocationName locationName;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Vehicule> vehicules;

    private Set<VehiculeType> allowedVehiculeTypes;

    public Location(int capacity, LocationName locationName, Set<VehiculeType> allowedVehiculeTypes,
            Set<Vehicule> vehicules) {
        this.capacity = capacity;
        this.locationName = locationName;
        this.allowedVehiculeTypes = allowedVehiculeTypes;
        this.vehicules = vehicules;
    }

    public boolean isAvailable() {
        return vehicules.size() < capacity;
    }
}
