package com.yarasoftware.workshopngine.platform.iam.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(user.getId(), user.getUsername(), user.getRole().getId(), user.getWorkshopId(), user.getStatus());
    }
}
