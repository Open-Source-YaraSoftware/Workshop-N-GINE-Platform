package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Task;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.TaskResource;

public class TaskResourceFromEntityAssembler {
    public static TaskResource toResourceFromEntity(Task entity) {
        return new TaskResource(entity.getId(), entity.getDescription(), entity.getState().name(), entity.getInventoryRequestState().name(), entity.getAssistantId(), entity.getInterventionId());
    }
}


