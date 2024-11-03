package com.yarasoftware.workshopngine.platform.service.infrastructure.persistence.jpa.repositories;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Task;
import com.yarasoftware.workshopngine.platform.service.domain.model.entities.Checkpoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Task entities.
 *
 * <p>
 * This repository provides methods to perform CRUD operations on Task entities,
 * as well as custom queries to find checkpoints by task ID and tasks by intervention ID and assistant ID.
 * </p>
 *
 * @since v1.0.0
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByInterventionId(Long interventionId);

    List<Task> findAllByInterventionIdAndAssistantId(Long interventionId, Long assistantId);

    Boolean existsByDescription(String description);

    Boolean existsByInterventionId(Long interventionId);
}