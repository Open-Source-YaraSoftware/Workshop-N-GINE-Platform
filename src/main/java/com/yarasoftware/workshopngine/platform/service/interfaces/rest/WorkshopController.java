package com.yarasoftware.workshopngine.platform.service.interfaces.rest;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllClientsByWorkshopIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllMechanicsByWorkshopIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetWorkshopByIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.UserId;
import com.yarasoftware.workshopngine.platform.service.domain.services.WorkshopCommandService;
import com.yarasoftware.workshopngine.platform.service.domain.services.WorkshopQueryService;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CreateClientResource;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CreateMechanicResource;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CreateWorkshopResource;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.WorkshopResource;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform.CreateClientCommandFromResourceAssembler;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform.CreateMechanicCommandFromResourceAssembler;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform.CreateWorkshopCommandFromResourceAssembler;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform.WorkshopResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/workshops")
@Tag(name = "Workshops", description = "Available Workshop Endpoints")
public class WorkshopController {
    private final WorkshopQueryService workshopQueryService;
    private final WorkshopCommandService workshopCommandService;

    public WorkshopController(WorkshopQueryService workshopQueryService, WorkshopCommandService workshopCommandService) {
        this.workshopQueryService = workshopQueryService;
        this.workshopCommandService = workshopCommandService;
    }

    @PostMapping
    @Operation(summary = "Create a new workshop", description = "Create a new workshop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Workshop created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Workshop not found")
    })
    public ResponseEntity<WorkshopResource> createWorkshop(@RequestBody CreateWorkshopResource resource) {
        var createWorkshopCommand = CreateWorkshopCommandFromResourceAssembler.toCommandFromResource(resource);
        var workshopId = workshopCommandService.handle(createWorkshopCommand);
        if (workshopId == null || workshopId == 0L) return ResponseEntity.badRequest().build();
        var getWorkshopByIdQuery = new GetWorkshopByIdQuery(workshopId);
        var workshop = workshopQueryService.handle(getWorkshopByIdQuery);
        if (workshop.isEmpty()) return ResponseEntity.notFound().build();
        var workshopEntity = workshop.get();
        var workshopResource = WorkshopResourceFromEntityAssembler.toResourceFromEntity(workshopEntity);
        return new ResponseEntity<>(workshopResource, HttpStatus.CREATED);
    }

    @GetMapping("/{workshopId}")
    @Operation(summary = "Get a workshop by id", description = "Get a workshop by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workshop found"),
            @ApiResponse(responseCode = "404", description = "Workshop not found")
    })
    public ResponseEntity<WorkshopResource> getWorkshopById(@PathVariable Long workshopId) {
        var getWorkshopByIdQuery = new GetWorkshopByIdQuery(workshopId);
        var workshop = workshopQueryService.handle(getWorkshopByIdQuery);
        if (workshop.isEmpty()) return ResponseEntity.notFound().build();
        var workshopEntity = workshop.get();
        var workshopResource = WorkshopResourceFromEntityAssembler.toResourceFromEntity(workshopEntity);
        return ResponseEntity.ok(workshopResource);
    }

    @GetMapping("/{workshopId}/clients")
    @Operation(summary = "Get all clients of a workshop", description = "Get all clients of a workshop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients found"),
            @ApiResponse(responseCode = "404", description = "Clients not found")
    })
    public ResponseEntity<List<Long>> getClientsByWorkshop(@PathVariable Long workshopId) {
        var getAllClientsByWorkshopQuery = new GetAllClientsByWorkshopIdQuery(workshopId);
        var clientsId = workshopQueryService.handle(getAllClientsByWorkshopQuery).stream().map(UserId::userId).toList();
        return ResponseEntity.ok(clientsId);
    }

    @GetMapping("/{workshopId}/mechanics")
    @Operation(summary = "Get all mechanics of a workshop", description = "Get all mechanics of a workshop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mechanics found"),
            @ApiResponse(responseCode = "404", description = "Mechanics not found")
    })
    public ResponseEntity<List<Long>> getMechanicsByWorkshop(@PathVariable Long workshopId) {
        var getAllMechanicsByWorkshopQuery = new GetAllMechanicsByWorkshopIdQuery(workshopId);
        var mechanics = workshopQueryService.handle(getAllMechanicsByWorkshopQuery).stream().map(UserId::userId).toList();
        return ResponseEntity.ok(mechanics);
    }

    @PostMapping("/{workshopId}/mechanics")
    @Operation(summary = "Create a new mechanic", description = "Create a new mechanic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mechanic created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Mechanic not found")
    })
    public ResponseEntity<Long> createMechanic(@PathVariable Long workshopId, @RequestBody CreateMechanicResource resource) {
        var createMechanicCommand = CreateMechanicCommandFromResourceAssembler.toCommandFromResourceAndPath(resource, workshopId);
        var mechanicId = workshopCommandService.handle(createMechanicCommand);
        if (mechanicId == null || mechanicId == 0L) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(mechanicId);
    }

    @PostMapping("/{workshopId}/clients")
    @Operation(summary = "Create a new client", description = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<Long> createClient(@PathVariable Long workshopId, @RequestBody CreateClientResource resource) {
        var createClientCommand = CreateClientCommandFromResourceAssembler.toCommandFromResourceAndPath(resource, workshopId);
        var clientId = workshopCommandService.handle(createClientCommand);
        if (clientId == null || clientId == 0L) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(clientId);
    }
}
