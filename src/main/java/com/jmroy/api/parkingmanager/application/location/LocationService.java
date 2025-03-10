package com.jmroy.api.parkingmanager.application.location;

import com.jmroy.api.parkingmanager.domain.location.Location;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(locationMapper::toDto)
                .collect(Collectors.toList());
    }

    public LocationDTO getLocationById(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));
        return locationMapper.toDto(location);
    }

    public LocationDTO createLocation(LocationDTO locationDTO) {
        Location location = locationMapper.toEntity(locationDTO);
        location = locationRepository.save(location);
        return locationMapper.toDto(location);
    }

    public LocationDTO updateLocation(Long id, LocationDTO locationDTO) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));
        location.setCapacity(locationDTO.getCapacity());
        location.setLocationName(locationDTO.getLocationName());
        location.setAllowedVehiculeTypes(locationDTO.getAllowedVehiculeTypes());
        location = locationRepository.save(location);
        return locationMapper.toDto(location);
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
