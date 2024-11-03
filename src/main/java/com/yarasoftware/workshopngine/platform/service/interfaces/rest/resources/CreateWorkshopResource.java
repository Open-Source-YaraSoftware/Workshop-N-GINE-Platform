package com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources;

public record CreateWorkshopResource(String name) {
    public CreateWorkshopResource {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
    }
}
