package com.yarasoftware.workshopngine.platform.service.domain.model.commands;

/**
 * Command to delete a checkpoint.
 * <p>
 * This command encapsulates the information required to delete a checkpoint within a task.
 * </p>
 *
 * @param checkpointId the ID of the checkpoint to delete
 * @since v1.0.0
 */
public record DeleteCheckpointCommand(Long checkpointId) {
    public DeleteCheckpointCommand {
        if (checkpointId == null || checkpointId <= 0)
            throw new IllegalArgumentException("Checkpoint ID must not be null");
    }
}
