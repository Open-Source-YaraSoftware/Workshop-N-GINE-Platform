package com.yarasoftware.workshopngine.platform.service.domain.model.commands;

public record CancelInterventionCommand(Long interventionId) {
    public CancelInterventionCommand {
        if (interventionId == null || interventionId <= 0) {
            throw new IllegalArgumentException("Invalid interventionId");
        }
    }
}
