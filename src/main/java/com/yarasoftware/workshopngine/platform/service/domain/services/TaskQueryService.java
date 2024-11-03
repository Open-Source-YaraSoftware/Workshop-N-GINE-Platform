package com.yarasoftware.workshopngine.platform.service.domain.services;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Task;
import com.yarasoftware.workshopngine.platform.service.domain.model.entities.Checkpoint;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for handling task-related queries.
 *
 * <p>
 * This service provides methods to handle various queries related to tasks,
 * such as retrieving tasks by intervention ID, assistant ID, and task ID.
 * </p>
 *
 * @since v1.0.0
 */
public interface TaskQueryService {
    /**
     * Handles the query to get all checkpoints by task ID.
     *
     * @param query the query to get all checkpoints by task ID
     * @return a list of tasks with their checkpoints
     */
    List<Checkpoint> handle(GetAllCheckpointsByTaskIdQuery query);

    /**
     * Handles the query to get all tasks by intervention ID and assistant ID.
     *
     * @param query the query to get all tasks by intervention ID and assistant ID
     * @return a list of tasks
     */
    List<Task> handle(GetAllTasksByInterventionIdAndAssistantIdQuery query);

    /**
     * Handles the query to get all tasks by intervention ID.
     *
     * @param query the query to get all tasks by intervention ID
     * @return a list of tasks
     */
    List<Task> handle(GetAllTasksByInterventionIdQuery query);

    /**
     * Handles the query to get a task by ID.
     *
     * @param query the query to get a task by ID
     * @return an optional task
     */
    Optional<Task> handle(GetTaskByIdQuery query);
}