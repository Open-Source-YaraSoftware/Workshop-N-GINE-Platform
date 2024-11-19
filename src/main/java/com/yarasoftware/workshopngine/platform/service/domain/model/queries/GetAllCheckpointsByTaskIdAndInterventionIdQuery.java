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
public record GetAllCheckpointsByTaskIdAndInterventionIdQuery(Long taskId, Long interventionId) {
    public GetAllCheckpointsByTaskIdAndInterventionIdQuery {
        if (taskId == null || taskId <= 0)
            throw new IllegalArgumentException("Task ID must not be null");
        if (interventionId == null || interventionId <= 0)
            throw new IllegalArgumentException("Intervention ID must not be null");
    }
}