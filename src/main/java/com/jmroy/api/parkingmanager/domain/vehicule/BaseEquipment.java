package com.jmroy.api.parkingmanager.domain.vehicule;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.jmroy.api.parkingmanager.domain.owner.Owner;
import com.jmroy.api.parkingmanager.domain.shared.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEquipment extends BaseEntity {

    protected String color;
    protected String licencePlate;

    @ManyToOne
    protected Owner owner;

}
