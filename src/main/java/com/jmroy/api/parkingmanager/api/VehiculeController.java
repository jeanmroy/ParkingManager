package com.jmroy.api.parkingmanager.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jmroy.api.parkingmanager.application.vehicule.VehiculeDTO;
import com.jmroy.api.parkingmanager.application.vehicule.VehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @GetMapping
    public List<VehiculeDTO> getAllVehicules() {
        return vehiculeService.getAllVehicules();
    }

    @GetMapping("/{id}")
    public VehiculeDTO getVehiculeById(@PathVariable Long id) {
        return vehiculeService.getVehiculeById(id);
    }

    @PostMapping
    public VehiculeDTO createVehicule(@RequestBody VehiculeDTO vehiculeDTO) {
        return vehiculeService.createVehicule(vehiculeDTO);
    }

    @PutMapping("/{id}")
    public VehiculeDTO updateVehicule(@PathVariable Long id, @RequestBody VehiculeDTO vehiculeDTO) {
        return vehiculeService.updateVehicule(id, vehiculeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }
}
