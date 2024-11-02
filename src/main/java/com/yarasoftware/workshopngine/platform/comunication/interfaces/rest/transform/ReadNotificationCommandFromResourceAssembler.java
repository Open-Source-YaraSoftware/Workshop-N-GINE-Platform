package com.yarasoftware.workshopngine.platform.comunication.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.comunication.domain.model.commands.ReadNotificationCommand;
import com.yarasoftware.workshopngine.platform.comunication.interfaces.rest.resources.ReadNotificationResource;

public class ReadNotificationCommandFromResourceAssembler {
    public static ReadNotificationCommand toCommandFromResources(ReadNotificationResource resource){
        return new ReadNotificationCommand(resource.notificationId());
    }
}
