package com.yarasoftware.workshopngine.platform.comunication.interfaces.rest.resources;

import com.yarasoftware.workshopngine.platform.comunication.domain.model.valueobjects.NotificationState;

import java.util.Date;

public record NotificationResource(
        Long id,
        Date date,
        String content,
        Long userId,
        NotificationState state,
        String endpoint
) {
}
