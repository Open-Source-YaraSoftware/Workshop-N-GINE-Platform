package com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources;

public record TaskResource(Long taskId, String description, String state, String inventoryRequestState, Long assistantId, Long interventionId) {
}
