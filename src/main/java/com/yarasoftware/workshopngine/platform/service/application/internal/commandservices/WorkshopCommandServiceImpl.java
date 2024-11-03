package com.yarasoftware.workshopngine.platform.service.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.service.application.internal.outboundservices.acl.ExternalIamService;
import com.yarasoftware.workshopngine.platform.service.application.internal.outboundservices.acl.ExternalProfilesService;
import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Workshop;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateClientCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateMechanicCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateWorkshopCommand;
import com.yarasoftware.workshopngine.platform.service.domain.services.WorkshopCommandService;
import com.yarasoftware.workshopngine.platform.service.infrastructure.persistence.jpa.repositories.WorkshopRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkshopCommandServiceImpl implements WorkshopCommandService {
    private final WorkshopRepository workshopRepository;
    private final ExternalIamService externalIamService;
    private final ExternalProfilesService externalProfilesService;

    public WorkshopCommandServiceImpl(WorkshopRepository workshopRepository, ExternalIamService externalIamService, ExternalProfilesService externalProfilesService) {
        this.workshopRepository = workshopRepository;
        this.externalIamService = externalIamService;
        this.externalProfilesService = externalProfilesService;
    }

    @Override
    public Long handle(CreateWorkshopCommand command) {
        if (workshopRepository.existsByName(command.name())) throw new IllegalArgumentException("Workshop with " + command.name() + " name already exists");
        var workshop = new Workshop(command.name());
        workshopRepository.save(workshop);
        return workshop.getId();
    }

    @Override
    public Long handle(CreateClientCommand command) {
        if (!workshopRepository.existsById(command.workshopId())) throw new IllegalArgumentException("Workshop does not exist");
        var userId = externalIamService.createUserWithRoleClient(command.email(), command.dni().toString(), command.workshopId());
        // TODO: Delete the created user if the profile creation fails
        var clientId = externalProfilesService.createProfile(command.firstName(), command.lastName(), command.dni(), command.email(), command.age(), command.location(), userId.orElseThrow().userId());
        return clientId.orElseThrow().profileId();
    }

    @Override
    public Long handle(CreateMechanicCommand command) {
        if (!workshopRepository.existsById(command.workshopId())) throw new IllegalArgumentException("Workshop does not exist");
        var userId = externalIamService.createUserWithRoleMechanic(command.email(), command.dni().toString(), command.workshopId());
        // TODO: Delete the created user if the profile creation fails
        var mechanicId = externalProfilesService.createProfile(command.firstName(), command.lastName(), command.dni(), command.email(), command.age(), command.location(), userId.orElseThrow().userId());
        return mechanicId.orElseThrow().profileId();
    }
}
