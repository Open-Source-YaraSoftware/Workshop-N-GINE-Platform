package com.yarasoftware.workshopngine.platform.service.infrastructure.persistence.jpa.repositories;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
    boolean existsByName(String name);
}
