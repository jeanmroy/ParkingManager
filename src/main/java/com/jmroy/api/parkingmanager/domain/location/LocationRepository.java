package com.jmroy.api.parkingmanager.domain.location;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

    boolean existsByLocationName(LocationName locationName);

}
