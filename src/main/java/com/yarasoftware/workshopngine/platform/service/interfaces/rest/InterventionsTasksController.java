package com.yarasoftware.workshopngine.platform.service.interfaces.rest;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateTaskCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.DeleteTaskCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.entities.Task;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllTasksByInterventionIdAndAssistantIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllTasksByInterventionIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.services.InterventionCommandService;
import com.yarasoftware.workshopngine.platform.service.domain.services.InterventionQueryService;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CreateTaskResource;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.TaskResource;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.UpdateTaskResource;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform.CreateTaskCommandFromResourceAssembler;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform.TaskResourceFromEntityAssembler;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform.UpdateTaskCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interventions/{interventionId}/tasks")
@Tag(name = "Interventions", description = "Available Intervention Endpoints")
public class InterventionsTasksController {
    private final InterventionCommandService interventionCommandService;
    private final InterventionQueryService interventionQueryService;

    public InterventionsTasksController(InterventionCommandService interventionCommandService, InterventionQueryService interventionQueryService) {
        this.interventionCommandService = interventionCommandService;
        this.interventionQueryService = interventionQueryService;
    }

    @GetMapping
    @Operation(summary = "Get all tasks for an intervention", description = "Get all tasks for an intervention")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks were found"),
            @ApiResponse(responseCode = "404", description = "Tasks were not found")
    })
    public ResponseEntity<List<TaskResource>> getAllTasks(@PathVariable Long interventionId, @RequestParam(required = false) Long assistantId) {
        List<Task> tasks;
        if (assistantId == null) {
            var getAllTasksByInterventionIdQuery = new GetAllTasksByInterventionIdQuery();
            tasks = interventionQueryService.handle(interventionId, getAllTasksByInterventionIdQuery);
        } else {
            var getAllTasksByInterventionIdAndAssistantIdQuery = new GetAllTasksByInterventionIdAndAssistantIdQuery(assistantId);
            tasks = interventionQueryService.handle(interventionId, getAllTasksByInterventionIdAndAssistantIdQuery);
        }
        var taskResources = tasks.stream()
                .map(TaskResourceFromEntityAssembler::ToResourceFromEntity)
                .toList();
        return ResponseEntity.ok(taskResources);
    }

    @PostMapping
    @Operation(summary = "Create a task for an intervention", description = "Create a task for an intervention")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task was created"),
            @ApiResponse(responseCode = "400", description = "Task was not created")
    })
    public ResponseEntity<TaskResource> createTask(@PathVariable Long interventionId, @RequestBody CreateTaskResource resource) {
        var createTaskCommand = CreateTaskCommandFromResourceAssembler.ToCommandFromResource(resource);
        var task = interventionCommandService.handle(interventionId, createTaskCommand);
        if (task.isEmpty()) return ResponseEntity.badRequest().build();
        var taskResource = TaskResourceFromEntityAssembler.ToResourceFromEntity(task.get());
        return new ResponseEntity<>(taskResource, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    @Operation(summary = "Update a task for an intervention", description = "Update a task for an intervention")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task was updated"),
            @ApiResponse(responseCode = "400", description = "Task was not updated")
    })
    public ResponseEntity<TaskResource> updateTask(@PathVariable Long interventionId, @PathVariable Long taskId, @RequestBody UpdateTaskResource resource) {
        var updateTaskCommand = UpdateTaskCommandFromResourceAssembler.ToCommandFromResource(resource);
        var task = interventionCommandService.handle(interventionId, taskId, updateTaskCommand);
        if (task.isEmpty()) return ResponseEntity.badRequest().build();
        var taskResource = TaskResourceFromEntityAssembler.ToResourceFromEntity(task.get());
        return new ResponseEntity<>(taskResource, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    @Operation(summary = "Delete a task for an intervention", description = "Delete a task for an intervention")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task was deleted"),
            @ApiResponse(responseCode = "400", description = "Task was not deleted")
    })
    public ResponseEntity<Void> deleteTask(@PathVariable Long interventionId, @PathVariable Long taskId) {
        var deleteTaskCommand = new DeleteTaskCommand(taskId);
        var task = interventionCommandService.handle(interventionId, deleteTaskCommand);
        if (!task) return ResponseEntity.badRequest().build();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
