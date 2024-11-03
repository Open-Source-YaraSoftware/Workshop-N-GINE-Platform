package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateCheckpointCommand;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CreateCheckpointResource;

public class CreateCheckpointCommandFromResourceAssembler {
    public static CreateCheckpointCommand toCommand(Long taskId, CreateCheckpointResource resource) {
        return new CreateCheckpointCommand(taskId, resource.name());
    }
}
