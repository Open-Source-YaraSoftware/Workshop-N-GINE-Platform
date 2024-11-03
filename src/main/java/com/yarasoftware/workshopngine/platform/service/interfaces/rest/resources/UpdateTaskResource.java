package com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources;

import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.InventoryRequestState;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.TaskState;

public record UpdateTaskResource(String description, Long assistantId, TaskState state, InventoryRequestState inventoryRequestState) {
}