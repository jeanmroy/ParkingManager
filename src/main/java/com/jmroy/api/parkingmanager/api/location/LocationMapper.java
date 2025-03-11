package com.jmroy.api.parkingmanager.api.location;

import com.jmroy.api.parkingmanager.api.vehicule.VehiculeMapper;
import com.jmroy.api.parkingmanager.domain.location.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { VehiculeMapper.class })
public interface LocationMapper {

    @Mapping(target = "vehicules", source = "vehicules")
    Location toEntity(LocationDTO locationDTO);

    @Mapping(target = "vehicules", source = "vehicules")
    LocationDTO toDto(Location location);
}
