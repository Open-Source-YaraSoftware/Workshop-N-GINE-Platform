package com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources;

public record CreateVehicleResource(
        String licensePlate,
        String brand,
        String model,
        Long clientId,
        Long iotDeviceId
) {
}
