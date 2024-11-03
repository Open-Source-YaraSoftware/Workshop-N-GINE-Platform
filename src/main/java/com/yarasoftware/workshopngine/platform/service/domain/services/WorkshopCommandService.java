package com.yarasoftware.workshopngine.platform.service.domain.services;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateClientCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateMechanicCommand;
import com.yarasoftware.workshopngine.platform.service.domain.model.commands.CreateWorkshopCommand;

public interface WorkshopCommandService {
    Long handle(CreateWorkshopCommand command);

    Long handle(CreateClientCommand command);

    Long handle(CreateMechanicCommand command);
}
