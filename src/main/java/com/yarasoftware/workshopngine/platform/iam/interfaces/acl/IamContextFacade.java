package com.yarasoftware.workshopngine.platform.iam.interfaces.acl;

import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.CreateUserCommand;
import com.yarasoftware.workshopngine.platform.iam.domain.model.queries.GetAllUsersByRoleAndWorkshopQuery;
import com.yarasoftware.workshopngine.platform.iam.domain.services.UserCommandService;
import com.yarasoftware.workshopngine.platform.iam.domain.services.UserQueryService;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * IamContextFacade is a class that provides the facade for the IAM context.
 */
@Service
public class IamContextFacade {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final RoleRepository roleRepository;

    /**
     * The constructor of the IamContextFacade.
     * @param userQueryService the {@link UserQueryService} to be used.
     * @param roleRepository the {@link RoleRepository} to be used.
     */
    public IamContextFacade(UserQueryService userQueryService, RoleRepository roleRepository, UserRepository userRepository, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.roleRepository = roleRepository;
        this.userCommandService = userCommandService;
    }

    /**
     * Get all users by role and workshop.
     * @param roleId the role id.
     * @param workshopId the workshop id.
     * @return a list of users
     */
    public List<Long> getAllUsersByRoleAndWorkshop(Long roleId, Long workshopId) {
        var role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        var getAllUsersByRoleAndWorkshop = new GetAllUsersByRoleAndWorkshopQuery(role, workshopId);
        return userQueryService.handle(getAllUsersByRoleAndWorkshop).stream().map(User::getId).toList();
    }

    /**
     * Create a user.
     * @param username the username.
     * @param password the password.
     * @param roleId the role id.
     * @param workshopId the workshop id.
     * @return the id of the created user.
     */
    public Long createUser(String username, String password, Long roleId, Long workshopId) {
        var role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        var createUserCommand = new CreateUserCommand(username, password, role, workshopId);
        return userCommandService.handle(createUserCommand);
    }
}