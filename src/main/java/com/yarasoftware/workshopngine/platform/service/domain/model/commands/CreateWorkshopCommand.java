package com.yarasoftware.workshopngine.platform.service.domain.model.commands;

public record CreateWorkshopCommand(String name) {
    public CreateWorkshopCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
    }
}
