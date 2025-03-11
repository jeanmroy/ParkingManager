package com.jmroy.api.parkingmanager.api.owner;

import java.util.Set;

import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;

import lombok.Data;

@Data
public class OwnerResource {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String note;
    private Set<Vehicule> vehicules;

}