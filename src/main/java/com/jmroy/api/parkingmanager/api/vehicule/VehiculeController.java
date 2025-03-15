package com.jmroy.api.parkingmanager.api.vehicule;

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

import com.jmroy.api.parkingmanager.application.vehicule.VehiculeForm;
import com.jmroy.api.parkingmanager.application.vehicule.VehiculeService;
import com.jmroy.api.parkingmanager.application.shared.ValidationService;
import com.jmroy.api.parkingmanager.domain.owner.Owner;
import com.jmroy.api.parkingmanager.domain.location.Location;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicules")
@Validated
public class VehiculeController {

    private final ValidationService validationService;
    private final VehiculeService vehiculeService;
    private final VehiculeMapper vehiculeMapper;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehiculeResource> getVehiculeById(@PathVariable Long id) {
        return ResponseEntity.ok().body(vehiculeMapper.toResource(vehiculeService.getVehiculeById(id)));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehiculeResource> createVehicule(@Valid @RequestBody VehiculeForm vehiculeForm,
            BindingResult bindingResult) {
        validationService.validateForm(bindingResult);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehiculeMapper.toResource(vehiculeService.createVehicule(vehiculeForm)));
    }

    @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehiculeResource> updateVehicule(@PathVariable Long id,
            @Valid @RequestBody VehiculeForm vehiculeForm,
            BindingResult bindingResult) {
        validationService.validateForm(bindingResult);

        return ResponseEntity.ok().body(vehiculeMapper.toResource(vehiculeService.updateVehicule(id, vehiculeForm)));
    }

    @PatchMapping(path = "/{id}/owner", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehiculeResource> patchVehiculeOwner(@PathVariable Long id,
            @Valid @RequestBody Owner owner,
            BindingResult bindingResult) {
        validationService.validateForm(bindingResult);

        return ResponseEntity.ok().body(vehiculeMapper.toResource(vehiculeService.patchOwner(id, owner)));
    }

    @PatchMapping(path = "/{id}/location", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehiculeResource> patchVehiculeLocation(@PathVariable Long id,
            @Valid @RequestBody Location location,
            BindingResult bindingResult) {
        validationService.validateForm(bindingResult);

        return ResponseEntity.ok().body(vehiculeMapper.toResource(vehiculeService.patchLocation(id, location)));
    }

}
