package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.UpdateCheckpointCommand;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.UpdateCheckpointResource;

public class UpdateCheckpointCommandFromResourceAssembler {
    public static UpdateCheckpointCommand toCommand(Long taskId, Long checkpointId, UpdateCheckpointResource resource) {
        return new UpdateCheckpointCommand(taskId, checkpointId, resource.name());
    }
}
