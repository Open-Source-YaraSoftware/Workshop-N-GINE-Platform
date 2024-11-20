package com.yarasoftware.workshopngine.platform.service.interfaces.rest;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.DeleteCheckpointCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllCheckpointsByTaskIdAndInterventionIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.services.InterventionCommandService;
import com.yarasoftware.workshopngine.platform.service.domain.services.InterventionQueryService;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CheckpointResource;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CreateCheckpointResource;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.UpdateCheckpointResource;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform.CheckpointResourceFromEntityAssembler;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform.CreateCheckpointCommandFromResourceAssembler;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform.UpdateCheckpointCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interventions/{interventionId}/tasks/{taskId}/checkpoints")
@Tag(name = "Interventions", description = "Available Intervention Endpoints")

public class InterventionTasksCheckpointsController {
    private final InterventionCommandService interventionCommandService;
    private final InterventionQueryService interventionQueryService;

    public InterventionTasksCheckpointsController(InterventionCommandService interventionCommandService, InterventionQueryService interventionQueryService) {
        this.interventionCommandService = interventionCommandService;
        this.interventionQueryService = interventionQueryService;
    }

    @GetMapping
    @Operation(summary = "Get all checkpoints for a task", description = "Get all checkpoints for a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Checkpoints were found"),
            @ApiResponse(responseCode = "404", description = "Checkpoints were not found")
    })
    public ResponseEntity<List<CheckpointResource>> getAllCheckpoints(@PathVariable Long interventionId, @PathVariable Long taskId) {
        var getAllCheckpointsByTaskIdAndInterventionIdQuery = new GetAllCheckpointsByTaskIdAndInterventionIdQuery(interventionId, taskId);
        var checkpoints = interventionQueryService.handle(getAllCheckpointsByTaskIdAndInterventionIdQuery);
        var checkpointResources = checkpoints.stream()
                .map(CheckpointResourceFromEntityAssembler::ToResourceFromEntity)
                .toList();
        return ResponseEntity.ok(checkpointResources);
    }

    @PostMapping
    @Operation(summary = "Create a checkpoint for a task", description = "Create a checkpoint for a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Checkpoint was created"),
            @ApiResponse(responseCode = "404", description = "Checkpoint was not created")
    })
    public ResponseEntity<CheckpointResource> createCheckpoint(@PathVariable Long interventionId, @PathVariable Long taskId, @RequestBody CreateCheckpointResource resource) {
        var createCheckpointCommand = CreateCheckpointCommandFromResourceAssembler.ToCommandFromResource(resource);
        var checkpoint = interventionCommandService.handle(interventionId, taskId, createCheckpointCommand);
        if (checkpoint.isEmpty()) return ResponseEntity.notFound().build();
        var checkpointResource = CheckpointResourceFromEntityAssembler.ToResourceFromEntity(checkpoint.get());
        return new ResponseEntity<>(checkpointResource, HttpStatus.CREATED);
    }

    @PutMapping("/{checkpointId}")
    @Operation(summary = "Update a checkpoint for a task", description = "Update a checkpoint for a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Checkpoint was updated"),
            @ApiResponse(responseCode = "404", description = "Checkpoint was not updated")
    })
    public ResponseEntity<CheckpointResource> updateCheckpoint(@PathVariable Long interventionId, @PathVariable Long taskId, @PathVariable Long checkpointId, @RequestBody UpdateCheckpointResource resource) {
        var updateCheckpointCommand = UpdateCheckpointCommandFromResourceAssembler.ToCommandFromResource(resource);
        var checkpoint = interventionCommandService.handle(interventionId, taskId, checkpointId, updateCheckpointCommand);
        if (checkpoint.isEmpty()) return ResponseEntity.notFound().build();
        var checkpointResource = CheckpointResourceFromEntityAssembler.ToResourceFromEntity(checkpoint.get());
        return new ResponseEntity<>(checkpointResource, HttpStatus.OK);
    }

    @DeleteMapping("/{checkpointId}")
    @Operation(summary = "Delete a checkpoint for a task", description = "Delete a checkpoint for a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Checkpoint was deleted"),
            @ApiResponse(responseCode = "404", description = "Checkpoint was not deleted")
    })
    public ResponseEntity<Void> deleteCheckpoint(@PathVariable Long interventionId, @PathVariable Long taskId, @PathVariable Long checkpointId) {
        var deleteCheckpointCommand = new DeleteCheckpointCommand(checkpointId);
        var checkpoint = interventionCommandService.handle(interventionId, taskId, deleteCheckpointCommand);
        if (!checkpoint) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

}
