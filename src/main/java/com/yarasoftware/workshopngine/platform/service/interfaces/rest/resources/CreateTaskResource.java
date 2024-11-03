package com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources;

import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.InventoryRequestState;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.TaskState;

public record CreateTaskResource(String description, Long assistantId, Long interventionId, TaskState state, InventoryRequestState inventoryRequestState) {
}