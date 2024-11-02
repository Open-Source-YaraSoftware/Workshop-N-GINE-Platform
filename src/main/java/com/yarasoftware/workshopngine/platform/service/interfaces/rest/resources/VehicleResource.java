package com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources;

import java.util.Date;

public record VehicleResource(
        Long id,
        String licensePlate,
        String brand,
        String model,
        Date registrationDate,
        Long clientId,
        Long iotDeviceId) {
}
