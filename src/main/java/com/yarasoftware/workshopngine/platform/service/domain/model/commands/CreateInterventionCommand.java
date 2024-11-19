package com.yarasoftware.workshopngine.platform.service.domain.model.commands;

import java.time.LocalDateTime;

public record CreateInterventionCommand(Long workshopId, Long vehicleId, Long clientId, Long mechanicLeaderId, String description, LocalDateTime scheduledAt) {
    public CreateInterventionCommand {
        if (workshopId == null) {
            throw new IllegalArgumentException("workshopId is required");
        }
        if (vehicleId == null) {
            throw new IllegalArgumentException("vehicleId is required");
        }
        if (clientId == null) {
            throw new IllegalArgumentException("clientId is required");
        }
        if (mechanicLeaderId == null) {
            throw new IllegalArgumentException("mechanicLeaderId is required");
        }
        if (scheduledAt == null || scheduledAt.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("scheduledAt is required and must be in the future");
        }
    }
}
