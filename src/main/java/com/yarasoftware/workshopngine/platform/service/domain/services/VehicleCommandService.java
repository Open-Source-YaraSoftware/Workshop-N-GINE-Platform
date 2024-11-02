package com.yarasoftware.workshopngine.platform.service.domain.services;

import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateVehicleCommand;

public interface VehicleCommandService {
    Long handle(CreateVehicleCommand command);
}
