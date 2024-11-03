package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.entities.Checkpoint;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CheckpointResource;

public class CheckpointResourceFromEntityAssembler {
    public static CheckpointResource toResourceFromEntity(Checkpoint checkpoint) {
        return new CheckpointResource(
                checkpoint.getId(),
                checkpoint.getName(),
                checkpoint.getTask().getId()
        );
    }
}
