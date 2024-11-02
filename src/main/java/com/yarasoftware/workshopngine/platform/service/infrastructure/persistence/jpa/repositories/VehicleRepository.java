package com.yarasoftware.workshopngine.platform.service.infrastructure.persistence.jpa.repositories;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findAllByClientId(Long clientId);
}
