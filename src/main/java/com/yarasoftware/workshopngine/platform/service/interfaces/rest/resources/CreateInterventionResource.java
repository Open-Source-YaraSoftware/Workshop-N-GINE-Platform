package com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CreateInterventionResource(Long workshopId, Long vehicleId, Long clientId, Long mechanicLeaderId, String description, LocalDateTime scheduledAt) {
    public CreateInterventionResource {
        if (workshopId == null || workshopId <= 0) {
            throw new IllegalArgumentException("workshopId is required");
        }
        if (vehicleId == null || vehicleId <= 0) {
            throw new IllegalArgumentException("vehicleId is required");
        }
        if (clientId == null || clientId <= 0) {
            throw new IllegalArgumentException("clientId is required");
        }
        if (mechanicLeaderId == null || mechanicLeaderId <= 0) {
            throw new IllegalArgumentException("mechanicLeaderId is required");
        }
        if (scheduledAt == null || scheduledAt.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("scheduledDate is required and must be in the future");
        }
    }
}
