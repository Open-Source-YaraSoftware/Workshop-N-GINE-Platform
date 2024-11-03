package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateWorkshopCommand;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CreateWorkshopResource;

public class CreateWorkshopCommandFromResourceAssembler {
    public static CreateWorkshopCommand toCommandFromResource(CreateWorkshopResource resource) {
        return new CreateWorkshopCommand(resource.name());
    }
}
