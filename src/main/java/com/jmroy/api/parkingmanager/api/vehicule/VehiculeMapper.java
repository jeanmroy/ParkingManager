package com.jmroy.api.parkingmanager.api.vehicule;

import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {

    @Mapping(source = "owner", target = "owner")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "licencePlate", target = "licencePlate")
    Vehicule toEntity(VehiculeResource vehiculeResource);

    @Mapping(source = "owner", target = "owner")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "color", target = "color")
    @Mapping(source = "licencePlate", target = "licencePlate")
    VehiculeResource toResource(Vehicule vehicule);

    List<VehiculeResource> toResourceList(List<Vehicule> vehicule);

}