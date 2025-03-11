package com.jmroy.api.parkingmanager.application.owner;

import java.util.Set;

import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OwnerForm {
    private String email;
    @NotEmpty
    @Size(min = 1, max = 50)
    private String firstName;
    @NotEmpty
    @Size(min = 1, max = 50)
    private String lastName;
    @NotEmpty
    @Size(min = 1, max = 10)
    private String phoneNumber;
    private String note;
    private Set<Vehicule> vehicules;
}