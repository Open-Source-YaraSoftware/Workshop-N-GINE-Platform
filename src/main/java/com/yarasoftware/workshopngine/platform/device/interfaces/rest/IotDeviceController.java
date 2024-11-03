package com.yarasoftware.workshopngine.platform.device.interfaces.rest;

import com.yarasoftware.workshopngine.platform.device.domain.model.queries.GetAllIotDeviceByVehicleIdQuery;
import com.yarasoftware.workshopngine.platform.device.domain.services.IotDeviceQueryService;
import com.yarasoftware.workshopngine.platform.device.interfaces.rest.resources.IotDeviceResource;
import com.yarasoftware.workshopngine.platform.device.interfaces.rest.transform.IotDeviceResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/iot-devices")
@Tag(name = "Devices", description = "Iot Devices Management Endpoints")
public class IotDeviceController {
    private final IotDeviceQueryService iotDeviceQueryService;

    public IotDeviceController(IotDeviceQueryService iotDeviceQueryService) {
        this.iotDeviceQueryService = iotDeviceQueryService;
    }

    @GetMapping
    public ResponseEntity<List<IotDeviceResource>> getIotDevicesByVehicleId(@RequestParam Long vehicleId) {
        if (vehicleId == 0L) return ResponseEntity.badRequest().build();
        var getAllIotDeviceByVehicleIdQuery = new GetAllIotDeviceByVehicleIdQuery(vehicleId);
        var iotDevices = iotDeviceQueryService.handle(getAllIotDeviceByVehicleIdQuery);
        if (iotDevices.isEmpty()) return ResponseEntity.badRequest().build();
        var iotDeviceResources = iotDevices.stream()
                .map(IotDeviceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(iotDeviceResources);
    }

}
