package com.yarasoftware.workshopngine.platform.service.domain.model.queries;

public record GetAllInterventionsByWorkshopAndMechanicLeaderQuery(Long mechanicLeaderId, Long workshopId) {
    public GetAllInterventionsByWorkshopAndMechanicLeaderQuery {
        if (mechanicLeaderId == null || mechanicLeaderId <= 0) {
            throw new IllegalArgumentException("MechanicLeaderId cannot be null or negative");
        }
        if (workshopId == null || workshopId <= 0) {
            throw new IllegalArgumentException("WorkshopId cannot be null or negative");
        }
    }
}
