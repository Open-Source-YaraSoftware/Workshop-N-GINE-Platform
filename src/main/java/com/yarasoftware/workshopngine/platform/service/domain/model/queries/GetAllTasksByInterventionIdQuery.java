package com.yarasoftware.workshopngine.platform.service.domain.model.queries;
/**
 * Query to retrieve all tasks associated with a specific intervention.
 *
 * <p>
 * This query provides a way to fetch all tasks for a given intervention.
 * </p>
 *
 * @param interventionId the ID of the intervention
 */
public record GetAllTasksByInterventionIdQuery(Long interventionId) {
    public GetAllTasksByInterventionIdQuery {
        if (interventionId == null || interventionId <= 0)
            throw new IllegalArgumentException("Intervention ID must not be null");
    }
}
