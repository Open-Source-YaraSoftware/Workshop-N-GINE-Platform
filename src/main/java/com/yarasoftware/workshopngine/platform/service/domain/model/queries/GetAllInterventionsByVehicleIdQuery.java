package com.yarasoftware.workshopngine.platform.service.domain.model.queries;

public record GetAllInterventionsByVehicleIdQuery(long vehicleId) {
    public GetAllInterventionsByVehicleIdQuery {
        if (vehicleId <= 0) {
            throw new IllegalArgumentException("Vehicle ID must be greater than 0");
        }
    }
}
