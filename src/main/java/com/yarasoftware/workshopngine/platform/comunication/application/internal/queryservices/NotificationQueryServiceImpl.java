package com.yarasoftware.workshopngine.platform.comunication.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.comunication.domain.model.aggregates.Notification;
import com.yarasoftware.workshopngine.platform.comunication.domain.model.queries.GetAllNotificationsByUserIdQuery;
import com.yarasoftware.workshopngine.platform.comunication.domain.services.NotificationQueryService;
import com.yarasoftware.workshopngine.platform.comunication.infrastructure.persistance.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is the implementation of the NotificationQueryService interface.
 * It is responsible for handling the queries related to the Notification aggregate.
 * It uses the NotificationRepository to retrieve the Notification aggregate.
 */
@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {
    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    public List<Notification> handle(GetAllNotificationsByUserIdQuery query) {

        var notifications = notificationRepository.findAll();

        return notifications.stream().filter(notification -> notification.getUserId().equals(query.userId())).toList();
        //return notificationRepository.findAllByUserId(query.userId());
    }

}
