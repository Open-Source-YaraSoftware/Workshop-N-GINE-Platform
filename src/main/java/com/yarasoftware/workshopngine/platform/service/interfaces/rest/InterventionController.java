package com.yarasoftware.workshopngine.platform.service.interfaces.rest;

import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllInterventionsByVehicleIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetInterventionByIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.services.InterventionCommandService;
import com.yarasoftware.workshopngine.platform.service.domain.services.InterventionQueryService;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CreateInterventionResource;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.InterventionResource;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.UpdateInterventionResource;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform.CreateInterventionCommandFromResourceAssembler;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform.InterventionResourceFromEntityAssembler;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform.UpdateInterventionCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/interventions")
@Tag(name = "Interventions", description = "Available Intervention Endpoints")
public class InterventionController {
    private final InterventionCommandService interventionCommandService;
    private final InterventionQueryService interventionQueryService;

    public InterventionController(InterventionCommandService interventionCommandService, InterventionQueryService interventionQueryService) {
        this.interventionCommandService = interventionCommandService;
        this.interventionQueryService = interventionQueryService;
    }

    @GetMapping
    @Operation(summary = "Get all interventions", description = "Get all interventions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interventions found"),
            @ApiResponse(responseCode = "404", description = "Interventions not found")
    })
    public ResponseEntity<List<InterventionResource>> getAllInterventions(@RequestParam Long vehicleId) {
        if (vehicleId == 0L) return ResponseEntity.badRequest().build();
        var getAllInterventionsByVehicleIdQuery = new GetAllInterventionsByVehicleIdQuery(vehicleId);
        var interventions = interventionQueryService.handle(getAllInterventionsByVehicleIdQuery);
        var interventionResources = interventions.stream()
                .map(InterventionResourceFromEntityAssembler::ToResourceFromEntity)
                .toList();
        return ResponseEntity.ok(interventionResources);
    }

    @GetMapping("/{interventionId}")
    @Operation(summary = "Get an intervention by id", description = "Get an intervention by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Intervention found"),
            @ApiResponse(responseCode = "404", description = "Intervention not found")
    })
    public ResponseEntity<InterventionResource> getInterventionById(@PathVariable long interventionId) {
        var getInterventionByIdQuery = new GetInterventionByIdQuery(interventionId);
        var intervention = interventionQueryService.handle(getInterventionByIdQuery);
        if (intervention.isEmpty()) return ResponseEntity.notFound().build();
        var interventionResource = InterventionResourceFromEntityAssembler.ToResourceFromEntity(intervention.get());
        return new ResponseEntity<>(interventionResource, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new intervention", description = "Create a new intervention")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Intervention created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Intervention not found")
    })
    public ResponseEntity<InterventionResource> createIntervention(@RequestBody CreateInterventionResource resource) {
        var createInterventionCommand = CreateInterventionCommandFromResourceAssembler.ToCommandFromResource(resource);
        var intervention = interventionCommandService.handle(createInterventionCommand);
        if (intervention.isEmpty()) return ResponseEntity.badRequest().build();
        var interventionResource = InterventionResourceFromEntityAssembler.ToResourceFromEntity(intervention.get());
        return new ResponseEntity<>(interventionResource, HttpStatus.CREATED);
    }

    @PutMapping("/{interventionId}")
    @Operation(summary = "Update an intervention", description = "Update an intervention")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Intervention updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Intervention not found")
    })
    public ResponseEntity<InterventionResource> updateIntervention(@PathVariable long interventionId, @RequestBody UpdateInterventionResource resource) {
        var updateInterventionCommand = UpdateInterventionCommandFromResourceAssembler.ToCommandFromResource(resource);
        var intervention = interventionCommandService.handle(interventionId, updateInterventionCommand);
        if (intervention.isEmpty()) return ResponseEntity.notFound().build();
        var interventionResource = InterventionResourceFromEntityAssembler.ToResourceFromEntity(intervention.get());
        return new ResponseEntity<>(interventionResource, HttpStatus.OK);
    }

}
