package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.UpdateCheckpointCommand;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.UpdateCheckpointResource;

public class UpdateCheckpointCommandFromResourceAssembler {
    public static UpdateCheckpointCommand ToCommandFromResource(UpdateCheckpointResource resource) {
        return new UpdateCheckpointCommand(resource.name());
    }
}
