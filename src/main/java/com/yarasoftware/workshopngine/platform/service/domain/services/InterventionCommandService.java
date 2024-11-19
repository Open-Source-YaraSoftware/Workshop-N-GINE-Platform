package com.yarasoftware.workshopngine.platform.service.domain.services;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Intervention;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateInterventionCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.UpdateInterventionCommand;

import java.util.Optional;

public interface InterventionCommandService {
    Optional<Intervention> handle(CreateInterventionCommand command);

    Optional<Intervention> handle(Long interventionId, UpdateInterventionCommand query);
}
