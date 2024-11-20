package com.yarasoftware.workshopngine.platform.service.domain.model.queries;

public record GetAllInterventionsByWorkshopIdQuery(Long workshopId) {
    public GetAllInterventionsByWorkshopIdQuery {
        if (workshopId == null || workshopId <= 0) {
            throw new IllegalArgumentException("WorkshopId must be a positive number");
        }
    }
}
