package com.jmroy.api.parkingmanager.application.location;

import com.jmroy.api.parkingmanager.domain.location.Location;
import com.jmroy.api.parkingmanager.domain.location.LocationRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

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

    public Location createLocation(LocationForm locationForm) {
        Location location = new Location(
                locationForm.getCapacity(),
                locationForm.getLocationName(),
                locationForm.getAllowedVehiculeTypes(),
                locationForm.getVehicules());

        return locationRepository.save(location);
    }

    public Location updateLocation(Long id, LocationForm locationForm) {
        Location location = getLocationById(id);

        location.setCapacity(locationForm.getCapacity());
        location.setLocationName(locationForm.getLocationName());
        location.setAllowedVehiculeTypes(locationForm.getAllowedVehiculeTypes());
        location.setVehicules(locationForm.getVehicules());

        return locationRepository.save(location);
    }

    // TODO: Think about deleting locations
    // Delete vs soft delete?
    // Do I want to delete an action location ? I won't physically destroy or remove
    // a parking.. or maybe?
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
