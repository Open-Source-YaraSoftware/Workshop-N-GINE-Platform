package com.yarasoftware.workshopngine.platform.service.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Vehicle;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateVehicleCommand;
import com.yarasoftware.workshopngine.platform.service.domain.services.VehicleCommandService;
import com.yarasoftware.workshopngine.platform.service.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleCommandServiceImpl implements VehicleCommandService {

    private final VehicleRepository vehicleRepository;

    public VehicleCommandServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Long handle(CreateVehicleCommand command) {
        var vehicle = new Vehicle(command.licensePlate(), command.brand(), command.model(), command.clientId(), command.iotDeviceId());
        vehicleRepository.save(vehicle);
        return vehicle.getId();
    }
}
