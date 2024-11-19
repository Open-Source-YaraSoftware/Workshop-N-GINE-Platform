package com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources;

import java.time.LocalDateTime;

public record UpdateInterventionResource(Long VehicleId, Long MechanicLeaderId, String Description, String Type, LocalDateTime ScheduledAt) {
    public UpdateInterventionResource {
        if (VehicleId == null || VehicleId <= 0) {
            throw new IllegalArgumentException("vehicleId is required");
        }
        if (MechanicLeaderId == null || MechanicLeaderId <= 0) {
            throw new IllegalArgumentException("mechanicLeaderId is required");
        }
        if (Description == null || Description.isBlank()) {
            throw new IllegalArgumentException("description is required");
        }
        if (Type == null || Type.isBlank()) {
            throw new IllegalArgumentException("type is required");
        }
        if (ScheduledAt == null || ScheduledAt.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("scheduledDate is required and must be in the future");
        }
    }
}
