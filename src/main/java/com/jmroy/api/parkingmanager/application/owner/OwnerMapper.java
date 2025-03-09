package com.jmroy.api.parkingmanager.application.owner;

import com.jmroy.api.parkingmanager.application.vehicule.VehiculeDTO;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {

    public Vehicule toEntity(VehiculeDTO vehiculeDTO) {
        // Implement the conversion logic from DTO to entity
        Vehicule vehicule = new Vehicule();
        vehicule.setBrand(vehiculeDTO.getBrand());
        vehicule.setModel(vehiculeDTO.getModel());
        vehicule.setNote(vehiculeDTO.getNote());
        vehicule.setType(vehiculeDTO.getType());
        // vehicule.setYear(vehiculeDTO.getYear());
        vehicule.setColor(vehiculeDTO.getColor());
        vehicule.setLicencePlate(vehiculeDTO.getLicencePlate());
        return vehicule;
    }

    public com.jmroy.api.parkingmanager.application.vehicule.VehiculeDTO toDto(Vehicule vehicule) {
        // Implement the conversion logic from entity to DTO
        VehiculeDTO vehiculeDTO = new VehiculeDTO();
        vehiculeDTO.setBrand(vehicule.getBrand());
        vehiculeDTO.setModel(vehicule.getModel());
        vehiculeDTO.setNote(vehicule.getNote());
        vehiculeDTO.setType(vehicule.getType());
        // vehiculeDTO.setYear(vehicule.getYear());
        vehiculeDTO.setColor(vehicule.getColor());
        vehiculeDTO.setLicencePlate(vehicule.getLicencePlate());
        return vehiculeDTO;
    }
}
