package com.yarasoftware.workshopngine.platform.service.domain.model.commands;

import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.InterventionTypes;

import java.time.LocalDateTime;

public record UpdateInterventionCommand(long vehicleId, long mechanicLeaderId, String description, InterventionTypes type, LocalDateTime scheduledAt) {
    public UpdateInterventionCommand {
        if (vehicleId <= 0) {
            throw new IllegalArgumentException("vehicleId is required");
        }
        if (mechanicLeaderId <= 0) {
            throw new IllegalArgumentException("mechanicLeaderId is required");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description is required");
        }
        if (type == null) {
            throw new IllegalArgumentException("type is required");
        }
        if (scheduledAt == null ) {
            throw new IllegalArgumentException("scheduledDate is required");
        }
    }
}
