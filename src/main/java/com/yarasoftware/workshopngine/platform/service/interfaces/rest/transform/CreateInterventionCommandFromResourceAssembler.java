package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateInterventionCommand;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CreateInterventionResource;

public class CreateInterventionCommandFromResourceAssembler {
    public static CreateInterventionCommand ToCommandFromResource(CreateInterventionResource resource) {
        return new CreateInterventionCommand(resource.workshopId(), resource.vehicleId(), resource.clientId(), resource.mechanicLeaderId(), resource.description(), resource.scheduledAt());
    }
}
