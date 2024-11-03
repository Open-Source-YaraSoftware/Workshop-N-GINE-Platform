package com.yarasoftware.workshopngine.platform.service.domain.model.commands;

public record CreateVehicleCommand(String licensePlate, String brand, String model, Long clientId, Long iotDeviceId) {
}
