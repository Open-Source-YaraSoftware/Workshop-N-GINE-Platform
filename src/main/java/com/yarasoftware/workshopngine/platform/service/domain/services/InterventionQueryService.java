package com.yarasoftware.workshopngine.platform.service.domain.services;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Intervention;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllInterventionsByVehicleIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetInterventionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface InterventionQueryService {
    Optional<Intervention> handle(GetInterventionByIdQuery query);

    List<Intervention> handle(GetAllInterventionsByVehicleIdQuery query);
}
