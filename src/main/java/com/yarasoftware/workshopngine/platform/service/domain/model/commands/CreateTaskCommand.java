package com.yarasoftware.workshopngine.platform.service.domain.model.commands;

/**
 * Command to create a new Task.
 *
 * <p>
 * This command encapsulates the necessary information to create a Task within an intervention.
 * </p>
 *
 * @param description           the description of the task
 * @param assistantId           the ID of the assistant assigned to the task
 * @since v1.0.0
 */
public record CreateTaskCommand(Long assistantId, String description) {
    public CreateTaskCommand {
        if (assistantId == null || assistantId <= 0) {
            throw new IllegalArgumentException("Assistant ID is required");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
    }
}
