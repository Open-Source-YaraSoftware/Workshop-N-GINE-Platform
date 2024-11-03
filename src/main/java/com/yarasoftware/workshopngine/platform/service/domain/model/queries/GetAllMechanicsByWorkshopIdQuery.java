package com.yarasoftware.workshopngine.platform.service.domain.model.queries;

public record GetAllMechanicsByWorkshopIdQuery(Long workshopId) {
    public GetAllMechanicsByWorkshopIdQuery {
        if (workshopId == null || workshopId <= 0) {
            throw new IllegalArgumentException("workshopId must be greater than 0");
        }
    }
}
