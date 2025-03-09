package com.jmroy.api.parkingmanager.application.location;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmroy.api.parkingmanager.domain.location.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
