package com.yarasoftware.workshopngine.platform.iam.application.acl;

import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.CreateUserCommand;
import com.yarasoftware.workshopngine.platform.iam.domain.model.queries.GetAllUsersByWorkshopAndRoleIsClientQuery;
import com.yarasoftware.workshopngine.platform.iam.domain.model.queries.GetAllUsersByWorkshopAndRoleIsMechanicQuery;
import com.yarasoftware.workshopngine.platform.iam.domain.model.queries.GetAllUsersByWorkshopAndRoleIsOwnerQuery;
import com.yarasoftware.workshopngine.platform.iam.domain.model.valueobjects.Roles;
import com.yarasoftware.workshopngine.platform.iam.domain.services.UserCommandService;
import com.yarasoftware.workshopngine.platform.iam.domain.services.UserQueryService;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.yarasoftware.workshopngine.platform.iam.interfaces.acl.IamContextFacade;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * IamContextFacade is a class that provides the facade for the IAM context.
 */
@Service
public class IamContextFacadeImpl implements IamContextFacade {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final RoleRepository roleRepository;

    /**
     * The constructor of the IamContextFacade.
     * @param userQueryService the {@link UserQueryService} to be used.
     * @param userCommandService the {@link UserCommandService} to be used.
     * @param roleRepository the {@link RoleRepository} to be used.
     */
    public IamContextFacadeImpl(UserQueryService userQueryService, RoleRepository roleRepository, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.roleRepository = roleRepository;
        this.userCommandService = userCommandService;
    }

    @Override
    public List<Long> fetchAllUserByWorkshopAndRoleIsClient(Long workshopId) {
        var getAllUsersByWorkshopAndRoleIsClientQuery = new GetAllUsersByWorkshopAndRoleIsClientQuery(workshopId);
        return userQueryService.handle(getAllUsersByWorkshopAndRoleIsClientQuery).stream().map(User::getId).toList();
    }

    @Override
    public List<Long> fetchAllUserByWorkshopAndRoleIsMechanic(Long workshopId) {
        var getAllUsersByWorkshopAndRoleIsMechanicQuery = new GetAllUsersByWorkshopAndRoleIsMechanicQuery(workshopId);
        return userQueryService.handle(getAllUsersByWorkshopAndRoleIsMechanicQuery).stream().map(User::getId).toList();
    }

    @Override
    public List<Long> fetchAllUserByWorkshopAndRoleIsOwner(Long workshopId) {
        var getAllUsersByWorkshopAndRoleIsOwnerQuery = new GetAllUsersByWorkshopAndRoleIsOwnerQuery(workshopId);
        return userQueryService.handle(getAllUsersByWorkshopAndRoleIsOwnerQuery).stream().map(User::getId).toList();
    }

    /**
     * Create a user.
     * @param username the username.
     * @param password the password.
     * @param roleId the role id.
     * @param workshopId the workshop id.
     * @return the id of the created user.
     */
    @Override
    public Long createUser(String username, String password, Long roleId, Long workshopId) {
        var role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        var roleList = List.of(role);
        var createUserCommand = new CreateUserCommand(username, password, roleList, workshopId);
        return userCommandService.handle(createUserCommand);
    }

    @Override
    public Long createUserWithRoleMechanic(String username, String password, Long workshopId) {
        var role = roleRepository.findByName(Roles.ROLE_MECHANIC).orElseThrow(() -> new RuntimeException("Role not found"));
        var roleList = List.of(role);
        var createUserCommand = new CreateUserCommand(username, password, roleList, workshopId);
        return userCommandService.handle(createUserCommand);
    }

    @Override
    public Long createUserWithRoleClient(String username, String password, Long workshopId) {
        var role = roleRepository.findByName(Roles.ROLE_CLIENT).orElseThrow(() -> new RuntimeException("Role not found"));
        var roleList = List.of(role);
        var createUserCommand = new CreateUserCommand(username, password, roleList, workshopId);
        return userCommandService.handle(createUserCommand);
    }
}