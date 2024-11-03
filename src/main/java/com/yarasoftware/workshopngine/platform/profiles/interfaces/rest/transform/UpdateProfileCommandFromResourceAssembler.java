package com.yarasoftware.workshopngine.platform.profiles.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.profiles.domain.model.commands.UpdateProfileCommand;
import com.yarasoftware.workshopngine.platform.profiles.interfaces.rest.resources.UpdateProfileResource;

/**
 * Assembler class to convert an UpdateProfileResource into an UpdateProfileCommand.
 *
 * <p>
 * This class provides a utility method to transform an {@link UpdateProfileResource} object,
 * typically received in a REST request, into an {@link UpdateProfileCommand} that can be used
 * in the application's domain layer.
 * </p>
 */
public class UpdateProfileCommandFromResourceAssembler {

    /**
     * Converts an UpdateProfileResource to an UpdateProfileCommand.
     *
     * @param resource the UpdateProfileResource containing updated profile details
     * @return an UpdateProfileCommand with the information from the resource
     */
    public static UpdateProfileCommand toCommandFromResource(UpdateProfileResource resource) {
        return new UpdateProfileCommand(
                resource.firstName(), resource.lastName(), resource.dni(),
                resource.email(), resource.age(), resource.location()
        );
    }
}
