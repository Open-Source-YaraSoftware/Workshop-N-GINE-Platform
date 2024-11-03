package com.yarasoftware.workshopngine.platform.iam.domain.model.queries;

public record GetAllUsersByWorkshopAndRoleIsClientQuery(Long workshopId) {
    public GetAllUsersByWorkshopAndRoleIsClientQuery {
        if (workshopId == null || workshopId <= 0) {
            throw new IllegalArgumentException("workshopId must be a positive number");
        }
    }
}
