package com.yarasoftware.workshopngine.platform.iam.application.internal.queryservices;
import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.queries.GetAllUsersByRoleAndWorkshopQuery;
import com.yarasoftware.workshopngine.platform.iam.domain.services.UserQueryService;
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

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method retrieves all users by role and workshop id.
     * @param query
     * @return
     * @inheritDoc
     */
    @Override
    public List<User> handle(GetAllUsersByRoleAndWorkshopQuery query) {
        return userRepository.findByRoleAndWorkshopId(query.role(), query.workshopId());
    }
}
