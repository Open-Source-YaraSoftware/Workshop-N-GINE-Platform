package com.yarasoftware.workshopngine.platform.service.domain.model.commands;

public record CompleteInterventionCommand(Long interventionId) {
    public CompleteInterventionCommand {
        if (interventionId == null || interventionId <= 0) {
            throw new IllegalArgumentException("Invalid interventionId");
        }
    }
}
