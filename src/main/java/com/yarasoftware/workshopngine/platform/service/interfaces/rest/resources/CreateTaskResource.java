package com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources;


public record CreateTaskResource(Long assistantId, String description) {
    public CreateTaskResource {
        if (assistantId == null || assistantId <= 0) {
            throw new IllegalArgumentException("assistantId is required");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description is required");
        }
    }
}