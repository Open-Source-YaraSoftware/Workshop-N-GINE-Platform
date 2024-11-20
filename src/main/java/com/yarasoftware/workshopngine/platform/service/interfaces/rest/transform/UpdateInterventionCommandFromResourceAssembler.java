package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.UpdateInterventionCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.InterventionTypes;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.UpdateInterventionResource;

public class UpdateInterventionCommandFromResourceAssembler {
    public static UpdateInterventionCommand ToCommandFromResource(UpdateInterventionResource resource) {
        InterventionTypes type;
        try {
           type = InterventionTypes.valueOf(resource.Type().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid intervention type");
        }
        return new UpdateInterventionCommand(resource.VehicleId(), resource.MechanicLeaderId(), resource.Description(), type, resource.ScheduledAt());
    }
}
