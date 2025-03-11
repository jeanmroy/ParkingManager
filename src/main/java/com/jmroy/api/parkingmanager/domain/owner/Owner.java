package com.jmroy.api.parkingmanager.domain.owner;

import java.util.Set;

import com.jmroy.api.parkingmanager.domain.shared.BaseEntity;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Owner extends BaseEntity {

    private String email;
    private String firstName;
    private String lastName;
    private String note;
    private String phoneNumber;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Vehicule> vehicules;

    public Owner(String email, String firstName, String lastName, String phoneNumber, String note,
            Set<Vehicule> vehicules) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.note = note;
        this.vehicules = vehicules;
    }
}
