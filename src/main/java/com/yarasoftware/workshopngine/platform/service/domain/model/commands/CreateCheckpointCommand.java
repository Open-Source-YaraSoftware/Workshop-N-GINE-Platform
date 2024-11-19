package com.yarasoftware.workshopngine.platform.service.domain.model.commands;
/**
 * Command to create a new Checkpoint.
 *
 * <p>
 * This command encapsulates the information required to create a new checkpoint within a task.
 * </p>
 *
 * @param name   the name of the checkpoint
 * @since v1.0.0
 */
public record CreateCheckpointCommand(String name) {
    public CreateCheckpointCommand {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name must not be null or blank");
    }
}
