package com.yarasoftware.workshopngine.platform.iam.application.internal.queryservices;
import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.queries.GetAllUsersByWorkshopAndRoleIsClientQuery;
import com.yarasoftware.workshopngine.platform.iam.domain.model.queries.GetAllUsersByWorkshopAndRoleIsMechanicQuery;
import com.yarasoftware.workshopngine.platform.iam.domain.model.queries.GetAllUsersByWorkshopAndRoleIsOwnerQuery;
import com.yarasoftware.workshopngine.platform.iam.domain.model.valueobjects.Roles;
import com.yarasoftware.workshopngine.platform.iam.domain.services.UserQueryService;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementation of the UserQueryService interface.
 * This class is responsible for handling the GetAllUsersByRoleAndWorkshopQuery.
 * @see UserQueryService
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserQueryServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> handle(GetAllUsersByWorkshopAndRoleIsOwnerQuery query) {
        var role = roleRepository.findByName(Roles.ROLE_WORKSHOP_OWNER).orElseThrow( () -> new RuntimeException("Role not found"));
        return userRepository.findAllByWorkshopIdAndRolesContains(query.workshopId(), role);
    }

    @Override
    public List<User> handle(GetAllUsersByWorkshopAndRoleIsClientQuery query) {
        var role = roleRepository.findByName(Roles.ROLE_CLIENT).orElseThrow( () -> new RuntimeException("Role not found"));
        return userRepository.findAllByWorkshopIdAndRolesContains(query.workshopId(), role);
    }

    @Override
    public List<User> handle(GetAllUsersByWorkshopAndRoleIsMechanicQuery query) {
        var role = roleRepository.findByName(Roles.ROLE_MECHANIC).orElseThrow( () -> new RuntimeException("Role not found"));
        return userRepository.findAllByWorkshopIdAndRolesContains(query.workshopId(), role);
    }
}
