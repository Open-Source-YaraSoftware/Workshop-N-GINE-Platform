package com.yarasoftware.workshopngine.platform.service.domain.model.commands;
/**
 * Command to update an existing Checkpoint.
 *
 * <p>
 * This command encapsulates the information necessary to update the details of a checkpoint.
 * </p>
 *
 * @param name         the updated name of the checkpoint
 * @since v1.0.0
 */
public record UpdateCheckpointCommand(String name) {
    public UpdateCheckpointCommand {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name must not be null or blank");
    }
}
