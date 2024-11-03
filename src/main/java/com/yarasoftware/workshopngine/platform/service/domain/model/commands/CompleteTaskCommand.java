package com.yarasoftware.workshopngine.platform.service.domain.model.commands;
/**
 * Command to end an existing Task.
 * <p>
 * This command encapsulates the ID of the Task that should be ended.
 * </p>
 *
 * @param taskId the ID of the task to end
 * @since v1.0.0
 */
public record CompleteTaskCommand(Long taskId) {
    public CompleteTaskCommand {
        if (taskId == null || taskId <= 0)
            throw new IllegalArgumentException("Task ID must not be null");
    }
}
