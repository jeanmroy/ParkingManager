package com.jmroy.api.parkingmanager.api.owner;

import java.util.List;
import java.util.Set;

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

import com.jmroy.api.parkingmanager.application.owner.OwnerForm;
import com.jmroy.api.parkingmanager.application.owner.OwnerService;
import com.jmroy.api.parkingmanager.application.shared.ValidationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/owners")
@Validated
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;
    private final ValidationService validationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OwnerResource>> getAllOwners() {
        return ResponseEntity.ok().body(ownerMapper.toResourceList(ownerService.getAllOwners()));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerResource> getOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok().body(ownerMapper.toResource(ownerService.getOwnerById(id)));
    }

    @GetMapping(path = "/{id}/vehicules", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Vehicule>> getOwnerVehicules(@PathVariable Long id) {
        return ResponseEntity.ok().body(ownerService.getOwnerVehicles(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerResource> createOwner(@Valid @RequestBody OwnerForm ownerForm,
            BindingResult bindingResult) {
        validationService.validateForm(bindingResult);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ownerMapper.toResource(ownerService.createOwner(ownerForm)));
    }

    @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerResource> updateOwner(
            @PathVariable Long id,
            @Valid @RequestBody OwnerForm ownerForm,
            BindingResult bindingResult) {
        validationService.validateForm(bindingResult);

        return ResponseEntity.ok().body(ownerMapper.toResource(ownerService.updateOwner(id, ownerForm)));
    }

}
