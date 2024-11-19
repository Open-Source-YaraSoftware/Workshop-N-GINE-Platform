package com.yarasoftware.workshopngine.platform.service.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Intervention;
import com.yarasoftware.workshopngine.platform.service.domain.model.entities.Checkpoint;
import com.yarasoftware.workshopngine.platform.service.domain.model.entities.Task;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.*;
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

    @Override
    public List<Task> handle(Long interventionId, GetAllTasksByInterventionIdQuery query) {
        var intervention = interventionRepository.findById(interventionId);
        return intervention.map(Intervention::getTasks).orElse(List.of());
    }

    @Override
    public List<Task> handle(Long interventionId, GetAllTasksByInterventionIdAndAssistantIdQuery query) {
        var intervention = interventionRepository.findById(interventionId);
        return intervention.map(i -> i.getAllTasksByAssistantId(query.assistantId())).orElse(List.of());
    }

    @Override
    public List<Checkpoint> handle(GetAllCheckpointsByTaskIdAndInterventionIdQuery query) {
        var intervention = interventionRepository.findById(query.interventionId());
        return intervention.map(i -> i.getAllCheckpointsByTaskId(query.taskId())).orElse(List.of());
    }
}
