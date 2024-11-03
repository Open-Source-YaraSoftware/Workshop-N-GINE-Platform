package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateClientCommand;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CreateClientResource;

public class CreateClientCommandFromResourceAssembler {
    public static CreateClientCommand toCommandFromResourceAndPath(CreateClientResource resource, Long workshopId) {
        return new CreateClientCommand(resource.firstName(), resource.lastName(), resource.dni(), resource.email(), resource.age(), resource.location(), workshopId);
    }
}
