package com.yarasoftware.workshopngine.platform.service.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Intervention;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateInterventionCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.UpdateInterventionCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.InterventionStatuses;
import com.yarasoftware.workshopngine.platform.service.domain.services.InterventionCommandService;
import com.yarasoftware.workshopngine.platform.service.infrastructure.persistence.jpa.repositories.InterventionRepository;
import com.yarasoftware.workshopngine.platform.service.infrastructure.persistence.jpa.repositories.WorkshopRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InterventionCommandServiceImpl implements InterventionCommandService {
    private final InterventionRepository interventionRepository;
    private final WorkshopRepository workshopRepository;

    public InterventionCommandServiceImpl(InterventionRepository interventionRepository, WorkshopRepository workshopRepository) {
        this.interventionRepository = interventionRepository;
        this.workshopRepository = workshopRepository;
    }

    @Override
    public Optional<Intervention> handle(CreateInterventionCommand command) {
        if(!workshopRepository.existsById(command.workshopId()))
            throw new IllegalArgumentException("Workshop with id %s not found".formatted(command.workshopId()));
        if(interventionRepository.existsByWorkshopIdAndScheduledAtIsBetween(command.workshopId(), command.scheduledAt().minusHours(2), command.scheduledAt().plusHours(2)))
            throw new IllegalArgumentException("Intervention already exists at %s in period %s - %s in workshop with id %s".formatted(command.scheduledAt(), command.scheduledAt().minusHours(2), command.scheduledAt().plusHours(2), command.workshopId()));
        // TODO: Only the role owner is able to do this
        if(interventionRepository.existsByWorkshopIdAndVehicleIdAndStatusIs(command.workshopId(), command.vehicleId(), InterventionStatuses.PENDING))
            throw new IllegalArgumentException("Intervention for vehicle with id %s already exists in workshop with id %s".formatted(command.vehicleId(), command.workshopId()));
        // TODO: Only the role owner is able to do this
        if (interventionRepository.existsByMechanicLeaderIdAndScheduledAtIsBetween(command.mechanicLeaderId(), command.scheduledAt().minusHours(1), command.scheduledAt().plusHours(5)))
            throw new IllegalArgumentException("Mechanic with id %s already has an intervention scheduled at %s in period %s - %s".formatted(command.mechanicLeaderId(), command.scheduledAt(), command.scheduledAt().minusHours(1), command.scheduledAt().plusHours(5)));
        var intervention = new Intervention(command);
        try {
            interventionRepository.save(intervention);
            return Optional.of(intervention);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving intervention: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public Optional<Intervention> handle(Long interventionId, UpdateInterventionCommand command) {
        var intervention = interventionRepository.findById(interventionId);
        if(intervention.isEmpty())
            throw new IllegalArgumentException("Intervention with id %s not found".formatted(interventionId));
        if(interventionRepository.existsByWorkshopIdAndScheduledAtIsBetween(intervention.get().getWorkshopId(), command.scheduledAt().minusHours(2), command.scheduledAt().plusHours(2)) && !intervention.get().getScheduledAt().equals(command.scheduledAt()))
            throw new IllegalArgumentException("Intervention already exists at %s in period %s - %s".formatted(command.scheduledAt(), command.scheduledAt().minusHours(2), command.scheduledAt().plusHours(2)));
        if(interventionRepository.existsByWorkshopIdAndVehicleIdAndStatusIs(intervention.get().getWorkshopId(), command.vehicleId(), InterventionStatuses.PENDING) && !intervention.get().getVehicleId().equals(command.vehicleId()))
            throw new IllegalArgumentException("Intervention for vehicle with id %s already exists in workshop with id %s".formatted(command.vehicleId(), intervention.get().getWorkshopId()));
        if (interventionRepository.existsByMechanicLeaderIdAndScheduledAtIsBetween(command.mechanicLeaderId(), command.scheduledAt().minusHours(1), command.scheduledAt().plusHours(5)))
            throw new IllegalArgumentException("Mechanic with id %s already has an intervention scheduled at %s in period %s - %s".formatted(command.mechanicLeaderId(), command.scheduledAt(), command.scheduledAt().minusHours(1), command.scheduledAt().plusHours(5)));
        intervention.get().Update(command);
        try {
            interventionRepository.save(intervention.get());
            return intervention;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving intervention: %s".formatted(e.getMessage()));
        }
    }
}
