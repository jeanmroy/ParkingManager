package com.jmroy.api.parkingmanager.api.vehicule;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jmroy.api.parkingmanager.application.vehicule.VehiculeForm;
import com.jmroy.api.parkingmanager.application.vehicule.VehiculeService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final VehiculeService vehiculeService;
    private final VehiculeMapper vehiculeMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehiculeResource>> getAllVehicules() {
        return ResponseEntity.ok().body(vehiculeMapper.toResourceList(vehiculeService.getAllVehicules()));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehiculeResource> getVehiculeById(@PathVariable Long id) {
        return ResponseEntity.ok().body(vehiculeMapper.toResource(vehiculeService.getVehiculeById(id)));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehiculeResource> createVehicule(@RequestBody VehiculeForm vehiculeForm) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehiculeMapper.toResource(vehiculeService.createVehicule(vehiculeForm)));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehiculeResource> updateVehicule(@PathVariable Long id,
            @RequestBody VehiculeForm vehiculeForm) {
        return ResponseEntity.ok().body(vehiculeMapper.toResource(vehiculeService.updateVehicule(id, vehiculeForm)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }
}
