package com.jmroy.api.parkingmanager.application.location;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.jmroy.api.parkingmanager.domain.location.Location;
import com.jmroy.api.parkingmanager.domain.location.LocationRepository;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
import com.jmroy.api.parkingmanager.exception.location.CapacityExceededException;
import com.jmroy.api.parkingmanager.exception.location.LocationNameAlreadyExistsException;
import com.jmroy.api.parkingmanager.exception.location.LocationNotFoundException;

import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException(id));
    }

    public Set<Vehicule> getLocationVehicules(Long id) {
        Location location = getLocationById(id);

        return location.getVehicules();
    }

    @Transactional
    public Location createLocation(LocationForm locationForm) {
        validateCapacity(locationForm.getCapacity(), new HashSet<>());

        Location location = new Location(
                locationForm.getCapacity(),
                locationForm.getLocationName(),
                locationForm.getAllowedVehiculeTypes(),
                new HashSet<>());

        return saveWithIntegrityValidation(location);
    }

    @Transactional
    public Location updateLocation(Long id, LocationForm locationForm) {
        Location location = getLocationById(id);
        Set<Vehicule> currentVehicules = getLocationVehicules(id);

        validateCapacity(locationForm.getCapacity(), currentVehicules);

        location.setCapacity(locationForm.getCapacity());
        location.setLocationName(locationForm.getLocationName());
        location.setAllowedVehiculeTypes(locationForm.getAllowedVehiculeTypes());

        return saveWithIntegrityValidation(location);
    }

    private void validateCapacity(int locationCapacity, Set<Vehicule> vehicules) {
        if (vehicules.size() > locationCapacity) {
            throw new CapacityExceededException(vehicules.size(), locationCapacity);
        }
    }

    private Location saveWithIntegrityValidation(Location location) {
        // Gestion de l'exception de contrainte d'unicité de locationName
        try {
            return locationRepository.save(location);

        } catch (DataIntegrityViolationException e) {
            throw new LocationNameAlreadyExistsException(location.getLocationName().toString());
        }
    }
}
