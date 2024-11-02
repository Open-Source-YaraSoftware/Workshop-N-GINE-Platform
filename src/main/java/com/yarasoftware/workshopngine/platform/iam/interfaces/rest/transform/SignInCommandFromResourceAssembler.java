package com.yarasoftware.workshopngine.platform.iam.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.iam.domain.model.commands.SignInCommand;
import com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources.SignInResource;

/**
 * SignInCommandFromResourceAssembler is a class that assembles the SignInCommand from the SignInResource.
 */
public class SignInCommandFromResourceAssembler {
    /**
     * The method toCommandFromResource assembles the SignInCommand from the SignInResource.
     * @param signInResource the SignInResource
     * @return the SignInCommand
     */
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}
