package com.yarasoftware.workshopngine.platform.service.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Vehicle;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllVehiclesByClientIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetVehicleByIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.services.VehicleQueryService;
import com.yarasoftware.workshopngine.platform.service.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleQueryServiceImpl implements VehicleQueryService {
    private final VehicleRepository vehicleRepository;

    public VehicleQueryServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> handle(GetAllVehiclesByClientIdQuery query) {
        return vehicleRepository.findAllByClientId(query.clientId());
    }

    @Override
    public Optional<Vehicle> handle(GetVehicleByIdQuery query) {
        return vehicleRepository.findById(query.id());
    }

}
