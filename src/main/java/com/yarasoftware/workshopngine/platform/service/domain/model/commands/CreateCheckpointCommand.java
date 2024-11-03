package com.yarasoftware.workshopngine.platform.service.domain.model.commands;
/**
 * Command to create a new Checkpoint.
 *
 * <p>
 * This command encapsulates the information required to create a new checkpoint within a task.
 * </p>
 *
 * @param taskId the ID of the task associated with the checkpoint
 * @param name   the name of the checkpoint
 * @since v1.0.0
 */
public record CreateCheckpointCommand(Long taskId, String name) {
    public CreateCheckpointCommand {
        if (taskId == null || taskId <= 0)
            throw new IllegalArgumentException("Task ID must not be null");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name must not be null or blank");
    }
}
