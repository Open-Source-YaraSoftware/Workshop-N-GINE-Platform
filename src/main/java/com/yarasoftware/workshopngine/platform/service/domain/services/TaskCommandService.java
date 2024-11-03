package com.yarasoftware.workshopngine.platform.service.domain.services;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Task;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.*;
import com.yarasoftware.workshopngine.platform.service.domain.model.entities.Checkpoint;

import java.util.Optional;

/**
 * Service interface for handling task-related commands.
 *
 * <p>
 * This service provides methods to handle various commands related to tasks,
 * such as creating, updating, deleting tasks and checkpoints.
 * </p>
 *
 * @since v1.0.0
 */
public interface TaskCommandService {
    /**
     * Handles the command to complete a task.
     *
     * @param command the command to complete a task
     * @return the ID of the completed task
     */
    Long handle(CompleteTaskCommand command);

    /**
     * Handles the command to create a checkpoint.
     *
     * @param command the command to create a checkpoint
     * @return the ID of the created checkpoint
     */
    Long handle(CreateCheckpointCommand command);

    /**
     * Handles the command to create a task.
     *
     * @param command the command to create a task
     * @return the ID of the created task
     */
    Long handle(CreateTaskCommand command);

    /**
     * Handles the command to delete a checkpoint.
     *
     * @param command the command to delete a checkpoint
     * @see DeleteCheckpointCommand
     */
    void handle(DeleteCheckpointCommand command);

    /**
     * Handles the command to delete a task.
     *
     * @param command the command to delete a task
     * @see DeleteTaskCommand
     */
    void handle(DeleteTaskCommand command);

    /**
     * Handles the command to update a checkpoint.
     *
     * @param command the command to update a checkpoint
     * @return the ID of the updated checkpoint
     */
    Optional<Checkpoint> handle(UpdateCheckpointCommand command);

    /**
     * Handles the command to update a task.
     *
     * @param command the command to update a task
     * @return the ID of the updated task
     */
    Optional<Task> handle(UpdateTaskCommand command);
}