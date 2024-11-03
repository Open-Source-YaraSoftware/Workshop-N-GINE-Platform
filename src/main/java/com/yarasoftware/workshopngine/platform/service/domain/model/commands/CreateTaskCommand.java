package com.yarasoftware.workshopngine.platform.service.domain.model.commands;

import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.InventoryRequestState;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.TaskState;

/**
 * Command to create a new Task.
 *
 * <p>
 * This command encapsulates the necessary information to create a Task within an intervention.
 * </p>
 *
 * @param description           the description of the task
 * @param assistantId           the ID of the assistant assigned to the task
 * @param interventionId        the ID of the associated intervention
 * @param state                 the initial state of the task
 * @param inventoryRequestState the initial state of the inventory request
 * @since v1.0.0
 */
public record CreateTaskCommand(String description, Long assistantId, Long interventionId, TaskState state, InventoryRequestState inventoryRequestState) {
    public CreateTaskCommand {
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("Description must not be null or blank");
        if (assistantId == null || assistantId <= 0)
            throw new IllegalArgumentException("Assistant ID must not be null");
        if (interventionId == null || interventionId <= 0)
            throw new IllegalArgumentException("Intervention ID must not be null");
        if (state == null)
            throw new IllegalArgumentException("Task state must not be null");
        if (inventoryRequestState == null)
            throw new IllegalArgumentException("Inventory request state must not be null");
    }
}
