package com.jmroy.api.parkingmanager.application.vehicule;

import com.jmroy.api.parkingmanager.application.owner.OwnerService;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
import com.jmroy.api.parkingmanager.domain.vehicule.VehiculeRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;

    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    public Vehicule getVehiculeById(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new VehiculeNotFoundException(id));
    }

    public Vehicule createVehicule(VehiculeForm vehiculeForm) {
        Vehicule vehicule = new Vehicule(
                vehiculeForm.getBrand(),
                vehiculeForm.getModel(),
                vehiculeForm.getNote(),
                vehiculeForm.getEqYear(),
                vehiculeForm.getType(),
                vehiculeForm.getLocation(),
                vehiculeForm.getColor(),
                vehiculeForm.getLicencePlate(),
                vehiculeForm.getOwner());

        return vehiculeRepository.save(vehicule);
    }

    public Vehicule updateVehicule(Long id, VehiculeForm vehiculeForm) {
        Vehicule vehicule = getVehiculeById(id);

        vehicule.setBrand(vehiculeForm.getBrand());
        vehicule.setModel(vehiculeForm.getModel());
        vehicule.setNote(vehiculeForm.getNote());
        vehicule.setEqYear(vehiculeForm.getEqYear());
        vehicule.setColor(vehiculeForm.getColor());
        vehicule.setLicencePlate(vehiculeForm.getLicencePlate());
        vehicule.setType(vehiculeForm.getType());
        vehicule.setLocation(vehiculeForm.getLocation());
        vehicule.setOwner(vehiculeForm.getOwner());

        return vehiculeRepository.save(vehicule);
    }

    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }
}
