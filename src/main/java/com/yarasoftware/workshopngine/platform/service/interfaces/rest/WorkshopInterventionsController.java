package com.yarasoftware.workshopngine.platform.service.interfaces.rest;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Intervention;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllInterventionsByWorkshopAndMechanicAssistantQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllInterventionsByWorkshopAndMechanicLeaderQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllInterventionsByWorkshopIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.services.InterventionQueryService;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.InterventionResource;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform.InterventionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workshops/{workshopId}/interventions")
@Tag(name = "Workshops", description = "Available Workshop Endpoints")
public class WorkshopInterventionsController {
    private final InterventionQueryService interventionQueryService;

    public WorkshopInterventionsController(InterventionQueryService interventionQueryService) {
        this.interventionQueryService = interventionQueryService;
    }

    @GetMapping
    @Operation(summary = "Get all interventions for a workshop", description = "Get all interventions for a workshop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interventions found"),
            @ApiResponse(responseCode = "404", description = "Interventions not found")
    })
    public ResponseEntity<List<InterventionResource>> getAllInterventionsByWorkshopId(@PathVariable Long workshopId, @RequestParam(required = false) Long mechanicLeaderId, @RequestParam(required = false) Long mechanicAssistantId) {
        List<Intervention> interventions;
        if (mechanicLeaderId != null) {
            var getAllInterventionsByWorkshopAndMechanicLeaderQuery = new GetAllInterventionsByWorkshopAndMechanicLeaderQuery(workshopId, mechanicLeaderId);
            interventions = interventionQueryService.handle(getAllInterventionsByWorkshopAndMechanicLeaderQuery);
        } else if (mechanicAssistantId != null) {
            var getAllInterventionsByWorkshopAndMechanicAssistantQuery = new GetAllInterventionsByWorkshopAndMechanicAssistantQuery(workshopId, mechanicAssistantId);
            interventions = interventionQueryService.handle(getAllInterventionsByWorkshopAndMechanicAssistantQuery);
        } else {
            var getAllInterventionsByWorkshopIdQuery = new GetAllInterventionsByWorkshopIdQuery(workshopId);
            interventions = interventionQueryService.handle(getAllInterventionsByWorkshopIdQuery);
        }
        var interventionResources = interventions.stream()
                .map(InterventionResourceFromEntityAssembler::ToResourceFromEntity)
                .toList();
        return ResponseEntity.ok(interventionResources);
    }
}
