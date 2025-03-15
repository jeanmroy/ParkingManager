package com.jmroy.api.parkingmanager.application.owner;

import com.jmroy.api.parkingmanager.domain.owner.Owner;
import com.jmroy.api.parkingmanager.domain.owner.OwnerRepository;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
import com.jmroy.api.parkingmanager.exception.owner.OwnerNotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Owner getOwnerById(Long id) {
        return ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException(id));
    }

    public Set<Vehicule> getOwnerVehicles(Long id) {
        Owner owner = getOwnerById(id);
        return owner.getVehicules();
    }

    /**
     * Creates a new owner based on the provided OwnerForm.
     * Vehicles are optional during creation.
     * @param ownerForm The form containing the owner's details. Must not be null.
     * @return The newly created and persisted Owner entity.
     */
    @Transactional
    public Owner createOwner(OwnerForm ownerForm) {
        Owner owner = new Owner(
                ownerForm.getEmail(),
                ownerForm.getFirstName(),
                ownerForm.getLastName(),
                ownerForm.getPhoneNumber(),
                ownerForm.getNote(),
                initializeVehicules(ownerForm.getVehicules()));

        return ownerRepository.save(owner);
    }

    /**
     * Updates an existing owner with the details provided in OwnerForm.
     * This method retrieves an owner based on its ID, updates its attributes with the new
     * information from the OwnerForm, and then persists the updated owner.
     * @param id The ID of the owner to update. Must not be null.
     * @param ownerForm The form containing the updated owner's details. Must not be null.
     * @return The updated Owner entity.
     * @throws OwnerNotFoundException if no owner is found with the given ID.
     */
    @Transactional
    public Owner updateOwner(Long id, OwnerForm ownerForm) {
        Owner owner = getOwnerById(id);

        owner.setEmail(ownerForm.getEmail());
        owner.setFirstName(ownerForm.getFirstName());
        owner.setLastName(ownerForm.getLastName());
        owner.setPhoneNumber(ownerForm.getPhoneNumber());
        owner.setNote(ownerForm.getNote());

        return ownerRepository.save(owner);
    }

    // Private validation methods

    private Set<Vehicule> initializeVehicules(Set<Vehicule> vehicules) {
        return vehicules != null ? new HashSet<>(vehicules) : new HashSet<>();
    }
}
