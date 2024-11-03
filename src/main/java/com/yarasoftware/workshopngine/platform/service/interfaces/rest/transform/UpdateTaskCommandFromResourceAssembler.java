package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.UpdateTaskCommand;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.UpdateTaskResource;

public class UpdateTaskCommandFromResourceAssembler {
    public static UpdateTaskCommand toCommand(Long taskId,UpdateTaskResource resource) {
        return new UpdateTaskCommand(taskId, resource.description(), resource.assistantId(), resource.state(), resource.inventoryRequestState());
    }
}