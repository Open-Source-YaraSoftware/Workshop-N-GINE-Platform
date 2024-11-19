package com.yarasoftware.workshopngine.platform.service.domain.model.commands;
/**
 * Command to update an existing Task.
 *
 * <p>
 * This command encapsulates the information necessary to update details of a Task, including its description,
 * assistant assignment, and states.
 * </p>
 *
 * @param description           the updated description of the task
 * @param assistantId           the updated ID of the assistant assigned to the task
 * @since v1.0.0
 */
public record UpdateTaskCommand(Long assistantId, String description) {
    public UpdateTaskCommand {
        if (assistantId == null || assistantId <= 0) {
            throw new IllegalArgumentException("Assistant ID is required");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
    }
}
