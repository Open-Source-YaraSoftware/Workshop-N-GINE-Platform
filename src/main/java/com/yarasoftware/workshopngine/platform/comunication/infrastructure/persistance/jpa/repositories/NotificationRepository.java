package com.yarasoftware.workshopngine.platform.comunication.infrastructure.persistance.jpa.repositories;

import com.yarasoftware.workshopngine.platform.comunication.domain.model.aggregates.Notification;
import com.yarasoftware.workshopngine.platform.comunication.domain.model.valueobjects.NotificationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByUserId(Long userId);
    boolean existsByIdAndStateIs(Long id, NotificationState state);
}
