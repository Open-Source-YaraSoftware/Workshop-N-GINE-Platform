package com.yarasoftware.workshopngine.platform.comunication.domain.services;

import com.yarasoftware.workshopngine.platform.comunication.domain.model.aggregates.Notification;
import com.yarasoftware.workshopngine.platform.comunication.domain.model.queries.GetAllNotificationsByUserIdQuery;

import java.util.List;

public interface NotificationQueryService {
    List<Notification> handle(GetAllNotificationsByUserIdQuery query);
}
