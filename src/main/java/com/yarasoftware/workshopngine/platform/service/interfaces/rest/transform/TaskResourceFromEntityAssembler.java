package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.entities.Task;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.TaskResource;

public class TaskResourceFromEntityAssembler {
    public static TaskResource ToResourceFromEntity(Task entity) {
        return new TaskResource(entity.getId(), entity.getAssistantId(), entity.getDescription(), entity.StatusToString(), entity.getIntervention().getId());
    }
}


