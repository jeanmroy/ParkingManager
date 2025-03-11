package com.jmroy.api.parkingmanager.api.location;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jmroy.api.parkingmanager.application.location.LocationForm;
import com.jmroy.api.parkingmanager.application.location.LocationService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;
    private final LocationMapper locationMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LocationResource>> getAllLocations() {
        return ResponseEntity.ok().body(locationMapper.toResourceList(locationService.getAllLocations()));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationResource> getLocationById(@PathVariable Long id) {
        return ResponseEntity.ok().body(locationMapper.toResource(locationService.getLocationById(id)));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationResource> createLocation(@RequestBody LocationForm locationForm) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(locationMapper.toResource(locationService.createLocation(locationForm)));
    }

    @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationResource> updateLocation(@PathVariable Long id,
            @RequestBody LocationForm locationForm) {
        return ResponseEntity.ok().body(locationMapper.toResource(locationService.updateLocation(id, locationForm)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
