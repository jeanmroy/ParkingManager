package com.jmroy.api.parkingmanager.domain.owner;

import java.util.Set;

import com.jmroy.api.parkingmanager.domain.shared.BaseEntity;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Owner extends BaseEntity {

    private String email;
    private String firstName;
    private String lastName;
    private String note;
    private String phone;

    @OneToMany(mappedBy = "owner")
    private Set<Vehicule> vehicules;
}
