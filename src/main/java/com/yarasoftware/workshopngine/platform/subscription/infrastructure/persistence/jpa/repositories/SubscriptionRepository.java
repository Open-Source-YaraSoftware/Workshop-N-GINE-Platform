package com.yarasoftware.workshopngine.platform.subscription.infrastructure.persistence.jpa.repositories;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByWorkshopId(Long workshopId);
}