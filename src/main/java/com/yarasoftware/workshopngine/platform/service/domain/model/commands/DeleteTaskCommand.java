package com.yarasoftware.workshopngine.platform.service.domain.model.commands;
/**
 * Command to delete an existing Task.
 *
 * <p>
 * This command encapsulates the ID of the Task that should be deleted.
 * </p>
 *
 * @param taskId the ID of the task to delete
 * @since v1.0.0
 */
public record DeleteTaskCommand(Long taskId) {
    public DeleteTaskCommand {
        if (taskId == null || taskId <= 0)
            throw new IllegalArgumentException("Task ID must not be null");
    }
}
