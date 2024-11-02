package com.yarasoftware.workshopngine.platform.comunication.domain.services;

import com.yarasoftware.workshopngine.platform.comunication.domain.model.commands.ReadNotificationCommand;

public interface NotificationCommandService {
    Long handle(ReadNotificationCommand command);
}
