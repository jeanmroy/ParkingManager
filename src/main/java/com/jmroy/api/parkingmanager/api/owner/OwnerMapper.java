package com.jmroy.api.parkingmanager.api.owner;

import com.jmroy.api.parkingmanager.domain.owner.Owner;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    Owner toEntity(OwnerResource ownerResource);

    OwnerResource toResource(Owner owner);

    List<OwnerResource> toResourceList(List<Owner> owner);

}