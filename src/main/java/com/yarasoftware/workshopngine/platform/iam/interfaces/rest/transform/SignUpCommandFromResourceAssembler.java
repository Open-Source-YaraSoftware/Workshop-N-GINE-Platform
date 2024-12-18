package com.yarasoftware.workshopngine.platform.iam.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.SignUpCommand;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Role;
import com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

/**
 * SignUpCommandFromResourceAssembler is a class that assembles the SignUpCommand from the SignUpResource.
 */
public class SignUpCommandFromResourceAssembler {
    /**
     * The method toCommandFromResource assembles the SignUpCommand from the SignUpResource.
     * @param signUpResource the user sign up resource
     * @return the SignUpCommand
     */
    public static SignUpCommand toCommandFromResource(SignUpResource signUpResource) {
        var roles = signUpResource.roles() != null
                ? signUpResource.roles().stream().map(Role::toRoleFromName).toList()
                : new ArrayList<Role>();
        System.out.println("roles: " + roles);
        return new SignUpCommand(signUpResource.username(), signUpResource.password(), roles, signUpResource.workshopId());
    }
}
