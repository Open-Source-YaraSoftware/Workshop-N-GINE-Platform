package com.yarasoftware.workshopngine.platform.comunication.infrastructure.persistance.jpa.repositories;

import com.yarasoftware.workshopngine.platform.comunication.domain.model.aggregates.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
