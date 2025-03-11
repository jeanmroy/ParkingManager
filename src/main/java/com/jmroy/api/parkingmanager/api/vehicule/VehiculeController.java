package com.jmroy.api.parkingmanager.api.vehicule;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jmroy.api.parkingmanager.application.vehicule.VehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehiculeDTO> getAllVehicules() {
        return vehiculeService.getAllVehicules();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehiculeDTO getVehiculeById(@PathVariable Long id) {
        return vehiculeService.getVehiculeById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public VehiculeDTO createVehicule(@RequestBody VehiculeDTO vehiculeDTO) {
        return vehiculeService.createVehicule(vehiculeDTO);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public VehiculeDTO updateVehicule(@PathVariable Long id, @RequestBody VehiculeDTO vehiculeDTO) {
        return vehiculeService.updateVehicule(id, vehiculeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }
}
