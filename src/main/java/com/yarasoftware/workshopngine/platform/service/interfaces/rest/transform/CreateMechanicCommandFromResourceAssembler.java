package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateMechanicCommand;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CreateMechanicResource;

public class CreateMechanicCommandFromResourceAssembler {
    public static CreateMechanicCommand toCommandFromResourceAndPath(CreateMechanicResource resource, Long workshopId) {
        return new CreateMechanicCommand(resource.firstName(), resource.lastName(), resource.dni(), resource.email(), resource.age(), resource.location(), workshopId);
    }
}
