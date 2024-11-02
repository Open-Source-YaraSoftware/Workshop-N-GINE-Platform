package com.yarasoftware.workshopngine.platform.service.domain.services;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Vehicle;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllVehiclesByClientIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetVehicleByIdQuery;

import java.util.List;
import java.util.Optional;

public interface VehicleQueryService {
    List<Vehicle> handle(GetAllVehiclesByClientIdQuery query);
    Optional<Vehicle> handle(GetVehicleByIdQuery query);
}
