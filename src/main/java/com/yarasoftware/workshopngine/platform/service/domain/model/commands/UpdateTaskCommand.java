package com.yarasoftware.workshopngine.platform.service.domain.model.commands;

import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.InventoryRequestState;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.TaskState;
/**
 * Command to update an existing Task.
 *
 * <p>
 * This command encapsulates the information necessary to update details of a Task, including its description,
 * assistant assignment, and states.
 * </p>
 *
 * @param taskId                the ID of the task to be updated
 * @param description           the updated description of the task
 * @param assistantId           the updated ID of the assistant assigned to the task
 * @param state                 the updated state of the task
 * @param inventoryRequestState the updated state of the inventory request
 * @since v1.0.0
 */
public record UpdateTaskCommand(Long taskId, String description, Long assistantId, TaskState state,
                                InventoryRequestState inventoryRequestState) {
    public UpdateTaskCommand {
        if (taskId == null || taskId <= 0)
            throw new IllegalArgumentException("Task ID must not be null");
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("Description must not be null or blank");
        if (assistantId == null || assistantId <= 0)
            throw new IllegalArgumentException("Assistant ID must not be null");
        if (state == null)
            throw new IllegalArgumentException("Task state must not be null");
        if (inventoryRequestState == null)
            throw new IllegalArgumentException("Inventory request state must not be null");
    }
}
