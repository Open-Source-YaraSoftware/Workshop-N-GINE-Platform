package com.yarasoftware.workshopngine.platform.comunication.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.comunication.domain.model.aggregates.Notification;
import com.yarasoftware.workshopngine.platform.comunication.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResourceFromEntity(Notification entity) {
        return new NotificationResource(entity.getId(), entity.getDate(), entity.getContent(), entity.getUserId(), entity.getState(), entity.getEndpoint());
    }
}
