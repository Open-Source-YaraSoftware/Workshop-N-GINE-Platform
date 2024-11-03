package com.yarasoftware.workshopngine.platform.iam.domain.model.queries;

public record GetAllUsersByWorkshopAndRoleIsMechanicQuery(Long workshopId) {
    public GetAllUsersByWorkshopAndRoleIsMechanicQuery {
        if (workshopId == null || workshopId <= 0) {
            throw new IllegalArgumentException("workshopId must be a positive number");
        }
    }
}
