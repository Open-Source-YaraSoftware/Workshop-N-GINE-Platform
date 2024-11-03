package com.yarasoftware.workshopngine.platform.service.domain.model.queries;
/**
 * Query to retrieve all checkpoints associated with a specific task.
 *
 * <p>
 * This query provides a way to fetch all checkpoints for a given task.
 * </p>
 *
 * @param taskId the ID of the task
 */
public record GetAllCheckpointsByTaskIdQuery(Long taskId) {
    public GetAllCheckpointsByTaskIdQuery {
        if (taskId == null || taskId <= 0)
            throw new IllegalArgumentException("Task ID must not be null");
    }
}