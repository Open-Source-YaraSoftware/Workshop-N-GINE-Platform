package com.yarasoftware.workshopngine.platform.service.domain.model.commands;

public record InProgressInterventionCommand(Long interventionId) {
    public InProgressInterventionCommand {
        if (interventionId == null || interventionId <= 0) {
            throw new IllegalArgumentException("Invalid interventionId");
        }
    }
}
