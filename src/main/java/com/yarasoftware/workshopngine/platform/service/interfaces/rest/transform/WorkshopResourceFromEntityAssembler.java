package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Workshop;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.WorkshopResource;

public class WorkshopResourceFromEntityAssembler {
    public static WorkshopResource toResourceFromEntity(Workshop workshop) {
        return new WorkshopResource(workshop.getId(), workshop.getName());
    }
}
