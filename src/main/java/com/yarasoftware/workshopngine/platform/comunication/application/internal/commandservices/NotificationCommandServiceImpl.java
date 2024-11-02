package com.yarasoftware.workshopngine.platform.comunication.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.comunication.domain.model.aggregates.Notification;
import com.yarasoftware.workshopngine.platform.comunication.domain.model.commands.ReadNotificationCommand;
import com.yarasoftware.workshopngine.platform.comunication.domain.services.NotificationCommandService;
import com.yarasoftware.workshopngine.platform.comunication.infrastructure.persistance.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

/**
 * This is the implementation of the NotificationCommandService interface.
 * It is responsible for handling the commands related to the Notification aggregate.
 * It uses the NotificationRepository to save the Notification aggregate.
 */
@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {
    private final NotificationRepository notificationRepository;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Long handle(ReadNotificationCommand command) {
        Notification notification = notificationRepository.findById(command.notificationId())
                .orElseThrow(() -> new IllegalArgumentException("Notification does not exist"));
        notification.read();
        notificationRepository.save(notification);

        return command.notificationId();
    }
}
