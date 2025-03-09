package com.jmroy.api.parkingmanager.application.owner;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmroy.api.parkingmanager.domain.owner.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
