package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Intervention;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.InterventionResource;

public class InterventionResourceFromEntityAssembler {
    public static InterventionResource ToResourceFromEntity(Intervention entity) {
        return new InterventionResource(entity.getId(), entity.getVehicleId(), entity.getClientId(), entity.getMechanicLeaderId(), entity.getDescription(), entity.TypeToString(), entity.StatusToString(), entity.getScheduledAt(), entity.getStartedAt(), entity.getFinishedAt());
    }
}
