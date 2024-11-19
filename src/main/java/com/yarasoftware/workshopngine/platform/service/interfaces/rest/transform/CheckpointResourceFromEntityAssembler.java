package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.entities.Checkpoint;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CheckpointResource;

public class CheckpointResourceFromEntityAssembler {
    public static CheckpointResource ToResourceFromEntity(Checkpoint entity) {
        return new CheckpointResource(entity.getId(), entity.getName(), entity.getTask().getId());
    }
}
