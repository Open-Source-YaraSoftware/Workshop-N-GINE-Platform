package com.yarasoftware.workshopngine.platform.subscription.infrastructure.persistence.jpa.repositories;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
}