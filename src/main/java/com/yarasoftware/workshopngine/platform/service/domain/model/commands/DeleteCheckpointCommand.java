package com.yarasoftware.workshopngine.platform.service.domain.model.commands;

/**
 * Command to delete a checkpoint.
 * <p>
 * This command encapsulates the information required to delete a checkpoint within a task.
 * </p>
 *
 * @param taskId       the ID of the task associated with the checkpoint
 * @param checkpointId the ID of the checkpoint to delete
 * @since v1.0.0
 */
public record DeleteCheckpointCommand(Long taskId, Long checkpointId) {
    public DeleteCheckpointCommand {
        if (taskId == null || taskId <= 0)
            throw new IllegalArgumentException("Task ID must not be null");
        if (checkpointId == null || checkpointId <= 0)
            throw new IllegalArgumentException("Checkpoint ID must not be null");
    }
}
