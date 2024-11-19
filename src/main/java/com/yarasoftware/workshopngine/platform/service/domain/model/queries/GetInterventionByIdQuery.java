package com.yarasoftware.workshopngine.platform.service.domain.model.queries;

public record GetInterventionByIdQuery(long interventionId) {
    public GetInterventionByIdQuery {
        if (interventionId <= 0) {
            throw new IllegalArgumentException("Intervention id must be greater than 0");
        }
    }
}
