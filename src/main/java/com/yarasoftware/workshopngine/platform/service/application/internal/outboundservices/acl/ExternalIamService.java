package com.yarasoftware.workshopngine.platform.service.application.internal.outboundservices.acl;

import com.yarasoftware.workshopngine.platform.iam.interfaces.acl.IamContextFacade;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.UserId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExternalIamService {
    private final IamContextFacade iamContextFacade;

    public ExternalIamService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public List<UserId> fetchAllUsersByWorkshopIdAndRoleIsClient(Long workshopId) {
        var userIds = iamContextFacade.fetchAllUserByWorkshopAndRoleIsClient(workshopId);
        return userIds.stream().map(UserId::new).toList();
    }

    public List<UserId> fetchAllUsersByWorkshopIdAndRoleIsMechanic(Long workshopId) {
        var userIds = iamContextFacade.fetchAllUserByWorkshopAndRoleIsMechanic(workshopId);
        return userIds.stream().map(UserId::new).toList();
    }

    public Optional<UserId> createUserWithRoleMechanic(String username, String password, Long workshopId) {
        var userId = iamContextFacade.createUserWithRoleMechanic(username, password, workshopId);
        return userId == 0L ? Optional.empty() : Optional.of(new UserId(userId));
    }

    public Optional<UserId> createUserWithRoleClient(String username, String password, Long workshopId) {
        var userId = iamContextFacade.createUserWithRoleClient(username, password, workshopId);
        return userId == 0L ? Optional.empty() : Optional.of(new UserId(userId));
    }
}
