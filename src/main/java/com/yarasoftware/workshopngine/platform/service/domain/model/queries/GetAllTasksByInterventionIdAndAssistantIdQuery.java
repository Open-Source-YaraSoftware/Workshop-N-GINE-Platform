package com.yarasoftware.workshopngine.platform.service.domain.model.queries;
/**
 * Query to retrieve all tasks associated with a specific intervention and assistant.
 *
 * <p>
 * This query provides a way to fetch tasks for a given intervention and assigned assistant.
 * </p>
 *
 * @param interventionId the ID of the intervention
 * @param assistantId    the ID of the assistant
 * @since v1.0.0
 */
public record GetAllTasksByInterventionIdAndAssistantIdQuery(Long interventionId, Long assistantId) {
    public GetAllTasksByInterventionIdAndAssistantIdQuery {
        if (interventionId == null || interventionId <= 0)
            throw new IllegalArgumentException("Intervention ID must not be null");
        if (assistantId == null || assistantId <= 0)
            throw new IllegalArgumentException("Assistant ID must not be null");
    }
}