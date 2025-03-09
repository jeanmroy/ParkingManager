package com.jmroy.api.parkingmanager.application.owner;

import com.jmroy.api.parkingmanager.domain.owner.Owner;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public OwnerService(OwnerRepository ownerRepository, OwnerMapper ownerMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }

    public Owner findById(Long ownerId) {
        return ownerRepository.findById(ownerId).orElseThrow(() -> new IllegalArgumentException("Owner not found"));
    }

    @Transactional
    public void addVehicule(Long ownerId, com.jmroy.api.parkingmanager.application.vehicule.VehiculeDTO vehiculeDTO) {
        Owner owner = findById(ownerId);
        Vehicule vehicule = ownerMapper.toEntity(vehiculeDTO);
        owner.getVehicules().add(vehicule);
        vehicule.setOwner(owner);
        ownerRepository.save(owner);
    }

    @Transactional
    public void removeVehicule(Long ownerId, Long vehiculeId) {
        Owner owner = findById(ownerId);
        Vehicule vehicule = findVehiculeById(vehiculeId);
        owner.getVehicules().remove(vehicule);
        vehicule.setOwner(null);
        ownerRepository.save(owner);
    }

    public Vehicule findVehiculeById(Long vehiculeId) {
        // Implement this method to find a Vehicule by its ID
        // This is a placeholder implementation
        return new Vehicule();
    }
}
