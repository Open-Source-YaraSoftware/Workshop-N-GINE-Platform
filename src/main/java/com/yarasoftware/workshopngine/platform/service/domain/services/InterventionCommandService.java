package com.yarasoftware.workshopngine.platform.service.domain.services;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Intervention;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.*;
import com.yarasoftware.workshopngine.platform.service.domain.model.entities.Checkpoint;
import com.yarasoftware.workshopngine.platform.service.domain.model.entities.Task;

import java.util.Optional;

public interface InterventionCommandService {
    Optional<Intervention> handle(CreateInterventionCommand command);

    Optional<Intervention> handle(Long interventionId, UpdateInterventionCommand query);

    Optional<Task> handle(Long interventionId, CreateTaskCommand command);

    Optional<Task> handle(Long interventionId, Long taskId, UpdateTaskCommand command);

    boolean handle(Long interventionId, DeleteTaskCommand command);

    Optional<Checkpoint> handle(Long interventionId, Long taskId, CreateCheckpointCommand command);

    Optional<Checkpoint> handle(Long interventionId, Long taskId, Long checkpointId, UpdateCheckpointCommand command);

    boolean handle(Long interventionId, Long taskId, DeleteCheckpointCommand command);

    Optional<Long> handle(InProgressInterventionCommand command);

    Optional<Long> handle(CompleteInterventionCommand command);

    Optional<Long> handle(CancelInterventionCommand command);

    Optional<Long> handle(Long interventionId, InProgressTaskCommand command);

    Optional<Long> handle(Long interventionId, CompleteTaskCommand command);
}
