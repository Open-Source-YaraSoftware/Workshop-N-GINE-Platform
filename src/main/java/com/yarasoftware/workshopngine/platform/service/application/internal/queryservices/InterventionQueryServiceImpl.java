package com.yarasoftware.workshopngine.platform.service.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Intervention;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllInterventionsByVehicleIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetInterventionByIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.services.InterventionQueryService;
import com.yarasoftware.workshopngine.platform.service.infrastructure.persistence.jpa.repositories.InterventionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterventionQueryServiceImpl implements InterventionQueryService {
    private final InterventionRepository interventionRepository;

    public InterventionQueryServiceImpl(InterventionRepository interventionRepository) {
        this.interventionRepository = interventionRepository;
    }

    @Override
    public Optional<Intervention> handle(GetInterventionByIdQuery query) {
        return interventionRepository.findById(query.interventionId());
    }

    @Override
    public List<Intervention> handle(GetAllInterventionsByVehicleIdQuery query) {
        return interventionRepository.findAllByVehicleId(query.vehicleId());
    }
}
