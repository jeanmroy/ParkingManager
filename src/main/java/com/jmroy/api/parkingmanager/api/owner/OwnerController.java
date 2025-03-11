package com.jmroy.api.parkingmanager.api.owner;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmroy.api.parkingmanager.application.owner.OwnerForm;
import com.jmroy.api.parkingmanager.application.owner.OwnerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OwnerResource>> getAllOwners() {
        return ResponseEntity.ok().body(ownerMapper.toResourceList(ownerService.getAllOwners()));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerResource> getOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok().body(ownerMapper.toResource(ownerService.getOwnerById(id)));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerResource> createOwner(@RequestBody OwnerForm ownerForm) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ownerMapper.toResource(ownerService.createOwner(ownerForm)));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerResource> updateOwner(@PathVariable Long id, @RequestBody OwnerForm ownerForm) {
        return ResponseEntity.ok().body(ownerMapper.toResource(ownerService.updateOwner(id, ownerForm)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }
}
