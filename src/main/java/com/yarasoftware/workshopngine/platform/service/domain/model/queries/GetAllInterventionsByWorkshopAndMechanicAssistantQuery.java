package com.yarasoftware.workshopngine.platform.service.domain.model.queries;

public record GetAllInterventionsByWorkshopAndMechanicAssistantQuery(Long mechanicAssistantId, Long workshopId) {
    public GetAllInterventionsByWorkshopAndMechanicAssistantQuery {
        if (mechanicAssistantId == null || mechanicAssistantId <= 0) {
            throw new IllegalArgumentException("MechanicAssistantId cannot be null or negative");
        }
        if (workshopId == null || workshopId <= 0) {
            throw new IllegalArgumentException("WorkshopId cannot be null or negative");
        }
    }
}
