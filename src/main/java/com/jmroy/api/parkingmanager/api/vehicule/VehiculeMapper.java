package com.jmroy.api.parkingmanager.api.vehicule;

import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {

    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "licencePlate", target = "licencePlate")
    VehiculeDTO toDto(Vehicule vehicule);

    @Mapping(source = "ownerId", target = "owner.id")
    @Mapping(source = "locationId", target = "location.id")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "licencePlate", target = "licencePlate")
    Vehicule toEntity(VehiculeDTO vehiculeDTO);
}