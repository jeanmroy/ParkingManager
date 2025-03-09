package com.jmroy.api.parkingmanager.application.location;

import com.jmroy.api.parkingmanager.application.vehicule.VehiculeDTO;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public Vehicule toEntity(VehiculeDTO vehiculeDTO) {
        // Implement the conversion logic from DTO to entity
        Vehicule vehicule = new Vehicule();
        vehicule.setBrand(vehiculeDTO.getBrand());
        vehicule.setModel(vehiculeDTO.getModel());
        vehicule.setNote(vehiculeDTO.getNote());
        vehicule.setType(vehiculeDTO.getType());
        vehicule.setEqYear(vehiculeDTO.getYear());
        vehicule.setColor(vehiculeDTO.getColor());
        vehicule.setLicencePlate(vehiculeDTO.getLicencePlate());
        return vehicule;
    }

    public VehiculeDTO toDto(Vehicule vehicule) {
        // Implement the conversion logic from entity to DTO
        VehiculeDTO vehiculeDTO = new VehiculeDTO();
        vehiculeDTO.setBrand(vehicule.getBrand());
        vehiculeDTO.setModel(vehicule.getModel());
        vehiculeDTO.setNote(vehicule.getNote());
        vehiculeDTO.setType(vehicule.getType());
        vehiculeDTO.setYear(vehicule.getEqYear());
        vehiculeDTO.setColor(vehicule.getColor());
        vehiculeDTO.setLicencePlate(vehicule.getLicencePlate());
        return vehiculeDTO;
    }
}
