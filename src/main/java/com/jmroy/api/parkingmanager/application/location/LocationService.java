package com.jmroy.api.parkingmanager.application.location;

import com.jmroy.api.parkingmanager.application.vehicule.VehiculeDTO;
import com.jmroy.api.parkingmanager.domain.location.Location;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    @Transactional
    public void addVehicule(Long locationId, VehiculeDTO vehiculeDTO) {
        Location location = findById(locationId);
        Vehicule vehicule = locationMapper.toEntity(vehiculeDTO);
        if (location.isAvailable()) {
            location.getVehicules().add(vehicule);
            vehicule.setLocation(location);
            locationRepository.save(location);
        } else {
            throw new IllegalStateException("No available space in this location");
        }
    }

    @Transactional
    public void removeVehicule(Long locationId, Long vehiculeId) {
        Location location = findById(locationId);
        Vehicule vehicule = findVehiculeById(vehiculeId);
        location.getVehicules().remove(vehicule);
        vehicule.setLocation(null);
        locationRepository.save(location);
    }

    public Location findById(Long locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));
    }

    public Vehicule findVehiculeById(Long vehiculeId) {
        // Implement this method to find a Vehicule by its ID
        // This is a placeholder implementation
        return new Vehicule();
    }
}
