package com.yarasoftware.workshopngine.platform.service.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Task;
import com.yarasoftware.workshopngine.platform.service.domain.model.entities.Checkpoint;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllCheckpointsByTaskIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllTasksByInterventionIdAndAssistantIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllTasksByInterventionIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetTaskByIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.services.TaskQueryService;
import com.yarasoftware.workshopngine.platform.service.infrastructure.persistence.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskQueryServiceImpl implements TaskQueryService {
    private final TaskRepository taskRepository;

    public TaskQueryServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Checkpoint> handle(GetAllCheckpointsByTaskIdQuery query) {
        var task = taskRepository.findById(query.taskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return task.getAllCheckpoints();
    }

    @Override
    public List<Task> handle(GetAllTasksByInterventionIdAndAssistantIdQuery query) {
        return taskRepository.findAllByInterventionIdAndAssistantId(query.interventionId(), query.assistantId());
    }

    @Override
    public List<Task> handle(GetAllTasksByInterventionIdQuery query) {
        return taskRepository.findAllByInterventionId(query.interventionId());
    }

    @Override
    public Optional<Task> handle(GetTaskByIdQuery query){
        return taskRepository.findById(query.taskId());
    }
}