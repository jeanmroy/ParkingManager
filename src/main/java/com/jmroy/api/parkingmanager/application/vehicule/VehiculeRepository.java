package com.jmroy.api.parkingmanager.application.vehicule;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
}
