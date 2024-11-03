package com.yarasoftware.workshopngine.platform.service.domain.model.queries;

/**
 * Represents a query to get a task by its id.
 * <p>
 * A task id must be a positive number.
 * This query encapsulates the id of the task that should be retrieved.
 * </p>
 *
 * @param taskId the id of the task to retrieve
 * @since v1.0.0
 */
public record GetTaskByIdQuery(Long taskId) {
    public GetTaskByIdQuery {
        if (taskId == null || taskId < 0) {
            throw new IllegalArgumentException("Task id must not be null");
        }
    }
}
