package com.yarasoftware.workshopngine.platform.service.interfaces.rest;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateVehicleCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllVehiclesByClientIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetVehicleByIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.services.VehicleCommandService;
import com.yarasoftware.workshopngine.platform.service.domain.services.VehicleQueryService;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.CreateVehicleResource;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.VehicleResource;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform.VehicleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/vehicles")
@Tag(name = "Vehicles", description = "Vehicle Management Endpoints")
public class VehiclesController {
    private final VehicleQueryService vehicleQueryService;
    private final VehicleCommandService vehicleCommandService;

    public VehiclesController(VehicleQueryService vehicleQueryService, VehicleCommandService vehicleCommandService) {
        this.vehicleQueryService = vehicleQueryService;
        this.vehicleCommandService = vehicleCommandService;
    }

    @GetMapping
    public ResponseEntity<List<VehicleResource>> getAllVehiclesByClientId(@RequestParam Long clientId){
        var getVehicleByClientIdQuery = new GetAllVehiclesByClientIdQuery(clientId);
        var vehicles = vehicleQueryService.handle(getVehicleByClientIdQuery);
        if (vehicles.isEmpty()) return ResponseEntity.badRequest().build();
        var vehicleResources = vehicles.stream()
                .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(vehicleResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResource> getVehicleById(@PathVariable Long id){
        var getVehicleByIdQuery = new GetVehicleByIdQuery(id);
        var vehicle = vehicleQueryService.handle(getVehicleByIdQuery);
        if (vehicle.isEmpty()) return ResponseEntity.badRequest().build();
        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle.get());
        return ResponseEntity.ok(vehicleResource);
    }

    @PostMapping
    public ResponseEntity<VehicleResource> createVehicle(@RequestBody CreateVehicleResource createVehicleResource){
        var createVehicleCommand = new CreateVehicleCommand(
                createVehicleResource.licensePlate(),
                createVehicleResource.brand(),
                createVehicleResource.model(),
                createVehicleResource.clientId(),
                createVehicleResource.iotDeviceId());
        var vehicleId = vehicleCommandService.handle(createVehicleCommand);
        if (vehicleId == 0L) return ResponseEntity.badRequest().build();
        var getVehicleByIdQuery = new GetVehicleByIdQuery(vehicleId);
        var vehicle = vehicleQueryService.handle(getVehicleByIdQuery);
        if (vehicle.isEmpty()) return ResponseEntity.badRequest().build();
        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle.get());
        return new ResponseEntity<>(vehicleResource, HttpStatus.CREATED);
    }


}
