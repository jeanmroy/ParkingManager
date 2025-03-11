package com.jmroy.api.parkingmanager.api.location;

import com.jmroy.api.parkingmanager.api.vehicule.VehiculeMapper;
import com.jmroy.api.parkingmanager.domain.location.Location;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { VehiculeMapper.class })
public interface LocationMapper {

    @Mapping(target = "vehicules", source = "vehicules")
    Location toEntity(LocationResource locationResource);

    @Mapping(target = "vehicules", source = "vehicules")
    LocationResource toResource(Location location);

    List<LocationResource> toResourceList(List<Location> locations);
}
