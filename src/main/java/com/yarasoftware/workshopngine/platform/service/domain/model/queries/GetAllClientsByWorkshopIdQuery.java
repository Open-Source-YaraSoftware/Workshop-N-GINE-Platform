package com.yarasoftware.workshopngine.platform.service.domain.model.queries;

public record GetAllClientsByWorkshopIdQuery(Long workshopId) {
    public GetAllClientsByWorkshopIdQuery {
        if (workshopId == null || workshopId <= 0) {
            throw new IllegalArgumentException("workshopId must be a positive number");
        }
    }
}
