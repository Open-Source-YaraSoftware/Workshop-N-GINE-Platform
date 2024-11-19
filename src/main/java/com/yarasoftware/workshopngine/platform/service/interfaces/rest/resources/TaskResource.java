package com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources;

public record TaskResource(Long id, Long assistantId, String description, String status, Long interventionId) {
}
