package com.yarasoftware.workshopngine.platform.iam.domain.services;

import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
