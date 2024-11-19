package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateTaskCommand;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CreateTaskResource;

public class CreateTaskCommandFromResourceAssembler {
    public static CreateTaskCommand ToCommandFromResource(CreateTaskResource resource) {
        return new CreateTaskCommand(resource.assistantId(), resource.description());
    }
}
