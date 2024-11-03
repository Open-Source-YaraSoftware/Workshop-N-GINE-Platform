package com.yarasoftware.workshopngine.platform.service.interfaces.rest;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CompleteTaskCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.DeleteCheckpointCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.DeleteTaskCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllCheckpointsByTaskIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllTasksByInterventionIdAndAssistantIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllTasksByInterventionIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetTaskByIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.services.TaskCommandService;
import com.yarasoftware.workshopngine.platform.service.domain.services.TaskQueryService;
import com.yarasoftware.workshopngine.platform.service.infrastructure.persistence.jpa.repositories.TaskRepository;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.*;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Tasks", description = "Task Management Endpoints")
public class TasksController {
    private final TaskCommandService taskCommandService;
    private final TaskQueryService taskQueryService;
    private final TaskRepository taskRepository;

    public TasksController(TaskCommandService taskCommandService, TaskQueryService taskQueryService, TaskRepository taskRepository) {
        this.taskCommandService = taskCommandService;
        this.taskQueryService = taskQueryService;
        this.taskRepository = taskRepository;
    }

    @GetMapping("{taskId}/checkpoints")
    public ResponseEntity<List<CheckpointResource>> getAllCheckpointsByTaskId(@PathVariable Long taskId) {
        var query = new GetAllCheckpointsByTaskIdQuery(taskId);
        var checkpoints = taskQueryService.handle(query);
        if (checkpoints.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
        var resources = checkpoints.stream()
                .map(CheckpointResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping
    public ResponseEntity<List<TaskResource>> getAllTasksByInterventionIdAndOptionalAssistantId(
            @RequestParam Long interventionId,
            @RequestParam(required = false) Long assistantId) {

        List<TaskResource> resources;

        if (assistantId != null) {
            var query = new GetAllTasksByInterventionIdAndAssistantIdQuery(interventionId, assistantId);
            var tasks = taskQueryService.handle(query);
            resources = tasks.stream()
                    .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
        } else {
            var query = new GetAllTasksByInterventionIdQuery(interventionId);
            var tasks = taskQueryService.handle(query);
            resources = tasks.stream()
                    .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
        }

        if (resources.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");

        return ResponseEntity.ok(resources);
    }

}
