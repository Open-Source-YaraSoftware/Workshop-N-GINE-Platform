package com.yarasoftware.workshopngine.platform.iam.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.SignUpCommand;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Role;
import com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources.SignUpResource;

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
        var role = Role.toRoleFromId(signUpResource.roleId());
        return new SignUpCommand(signUpResource.username(), signUpResource.password(), role, signUpResource.workshopId());
    }
}
