package com.yarasoftware.workshopngine.platform.iam.interfaces.rest.transform;
import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources.AuthenticateUserResource;

/**
 * AuthenticateUserResourceFromEntityAssembler is a class that assembles the AuthenticateUserResource from the User entity.
 */
public class AuthenticateUserResourceFromEntityAssembler {
    /**
     * The method toResourceFromEntity assembles the AuthenticateUserResource from the User entity.
     * @param user the user entity
     * @return the AuthenticateUserResource
     */
    public static AuthenticateUserResource toResourceFromEntity(User user) {
        return new AuthenticateUserResource(user.getId(), user.getUsername(), user.getRole().getId(), user.getWorkshopId());
    }
}
