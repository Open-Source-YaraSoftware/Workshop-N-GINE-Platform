package com.yarasoftware.workshopngine.platform.iam.application.internal.commandservices;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Role;
import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.SeedRolesCommand;
import com.yarasoftware.workshopngine.platform.iam.domain.model.valueobjects.Roles;
import com.yarasoftware.workshopngine.platform.iam.domain.services.RoleCommandService;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import java.util.Arrays;

/**
 * Implementation of the RoleCommandService interface.
 * This class is responsible for handling the SeedRolesCommand.
 * @see RoleCommandService
 */
@Service
public class RoleCommandServiceImpl implements RoleCommandService {
    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if (!roleRepository.existsByName(role))
                roleRepository.save(new Role(Roles.valueOf(role.name())));
        });
    }
}
