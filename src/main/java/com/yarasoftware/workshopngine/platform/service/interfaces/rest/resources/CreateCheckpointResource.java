package com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources;

public record CreateCheckpointResource(String name) {
    public CreateCheckpointResource {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
    }
}
