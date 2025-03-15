package com.jmroy.api.parkingmanager.api.location;

import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;

import com.jmroy.api.parkingmanager.application.location.LocationForm;
import com.jmroy.api.parkingmanager.application.location.LocationService;
import com.jmroy.api.parkingmanager.application.shared.ValidationService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locations")
@Validated
public class LocationController {

    private final LocationService locationService;
    private final LocationMapper locationMapper;
    private final ValidationService validationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LocationResource>> getAllLocations() {
        return ResponseEntity.ok().body(locationMapper.toResourceList(locationService.getAllLocations()));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationResource> getLocationById(@PathVariable Long id) {
        return ResponseEntity.ok().body(locationMapper.toResource(locationService.getLocationById(id)));
    }

    @GetMapping(path = "/{id}/vehicules", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Vehicule>> getLocationVehicules(@PathVariable Long id) {
        return ResponseEntity.ok().body(locationService.getLocationVehicules(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationResource> createLocation(@Valid @RequestBody LocationForm locationForm,
            BindingResult bindingResult) {
        validationService.validateForm(bindingResult);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(locationMapper.toResource(locationService.createLocation(locationForm)));
    }

    @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationResource> updateLocation(@PathVariable Long id,
            @Valid @RequestBody LocationForm locationForm,
            BindingResult bindingResult) {
        validationService.validateForm(bindingResult);

        return ResponseEntity.ok().body(locationMapper.toResource(locationService.updateLocation(id, locationForm)));
    }

}
