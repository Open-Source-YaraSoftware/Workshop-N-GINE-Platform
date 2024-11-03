package com.yarasoftware.workshopngine.platform.service.domain.model.queries;

public record GetWorkshopByIdQuery(Long workshopId) {
    public GetWorkshopByIdQuery {
        if (workshopId == null || workshopId < 1) {
            throw new IllegalArgumentException("Workshop ID cannot be null or less than 1");
        }
    }
}
