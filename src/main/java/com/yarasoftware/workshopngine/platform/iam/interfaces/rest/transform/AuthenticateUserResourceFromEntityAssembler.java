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
    public static AuthenticateUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticateUserResource(
                user.getId(),
                user.getUsername(),
                user.getRoles().stream().map(role -> role.getName().name()).toList(),
                user.getWorkshopId(),
                token
        );
    }
}
