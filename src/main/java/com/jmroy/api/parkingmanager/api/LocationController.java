package com.jmroy.api.parkingmanager.api;

import com.jmroy.api.parkingmanager.application.location.LocationService;
import com.jmroy.api.parkingmanager.application.vehicule.VehiculeDTO;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/{locationId}/vehicules")
    public void addVehicule(@PathVariable Long locationId, @RequestBody VehiculeDTO vehiculeDTO) {
        locationService.addVehicule(locationId, vehiculeDTO);
    }

    @DeleteMapping("/{locationId}/vehicules/{vehiculeId}")
    public void removeVehicule(@PathVariable Long locationId, @PathVariable Long vehiculeId) {
        locationService.removeVehicule(locationId, vehiculeId);
    }
}
