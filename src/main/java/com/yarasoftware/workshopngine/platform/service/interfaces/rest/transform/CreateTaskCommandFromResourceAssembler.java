package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateTaskCommand;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CreateTaskResource;

public class CreateTaskCommandFromResourceAssembler {
    public static CreateTaskCommand toCommand(CreateTaskResource resource) {
        return new CreateTaskCommand(resource.description(), resource.assistantId(), resource.interventionId(), resource.state(), resource.inventoryRequestState());
    }
}
