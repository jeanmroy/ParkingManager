package com.jmroy.api.parkingmanager.api.location;

import com.jmroy.api.parkingmanager.domain.location.Location;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    Location toEntity(LocationResource locationResource);

    LocationResource toResource(Location location);

    List<LocationResource> toResourceList(List<Location> location);
}
