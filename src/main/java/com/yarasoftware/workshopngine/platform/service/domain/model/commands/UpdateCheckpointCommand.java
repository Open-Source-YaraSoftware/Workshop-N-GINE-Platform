package com.yarasoftware.workshopngine.platform.service.domain.model.commands;
/**
 * Command to update an existing Checkpoint.
 *
 * <p>
 * This command encapsulates the information necessary to update the details of a checkpoint.
 * </p>
 *
 * @param checkpointId the ID of the checkpoint to update
 * @param name         the updated name of the checkpoint
 * @since v1.0.0
 */
public record UpdateCheckpointCommand(Long taskId,Long checkpointId, String name) {
    public UpdateCheckpointCommand {
        if (taskId == null || taskId <= 0)
            throw new IllegalArgumentException("Task ID must not be null");
        if (checkpointId == null || checkpointId <= 0)
            throw new IllegalArgumentException("Checkpoint ID must not be null");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name must not be null or blank");
    }
}
