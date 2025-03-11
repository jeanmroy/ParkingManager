package com.jmroy.api.parkingmanager.application.owner;

import com.jmroy.api.parkingmanager.domain.owner.Owner;
import com.jmroy.api.parkingmanager.domain.owner.OwnerRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

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

    public Owner createOwner(OwnerForm ownerForm) {
        Owner owner = new Owner(
                ownerForm.getEmail(),
                ownerForm.getFirstName(),
                ownerForm.getLastName(),
                ownerForm.getPhoneNumber(),
                ownerForm.getNote(),
                ownerForm.getVehicules());

        return ownerRepository.save(owner);
    }

    public Owner updateOwner(Long id, OwnerForm ownerForm) {
        Owner owner = getOwnerById(id);

        owner.setEmail(ownerForm.getEmail());
        owner.setFirstName(ownerForm.getFirstName());
        owner.setLastName(ownerForm.getLastName());
        owner.setPhoneNumber(ownerForm.getPhoneNumber());
        owner.setNote(ownerForm.getNote());
        owner.setVehicules(ownerForm.getVehicules());

        return ownerRepository.save(owner);
    }

    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }
}
