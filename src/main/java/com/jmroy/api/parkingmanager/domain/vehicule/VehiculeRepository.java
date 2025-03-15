package com.jmroy.api.parkingmanager.domain.vehicule;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

    boolean existsByLicencePlate(String licencePlate);
}
