package com.jmroy.api.parkingmanager.api.owner;

import com.jmroy.api.parkingmanager.domain.owner.Owner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    OwnerDTO toDto(Owner owner);

    Owner toEntity(OwnerDTO ownerDTO);
}