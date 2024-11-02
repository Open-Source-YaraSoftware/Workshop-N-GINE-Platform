package com.yarasoftware.workshopngine.platform.profiles.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.yarasoftware.workshopngine.platform.profiles.interfaces.rest.resources.CreateProfileResource;

/**
 * Assembler class to convert a CreateProfileResource into a CreateProfileCommand.
 *
 * <p>
 * This class provides a utility method to transform a {@link CreateProfileResource} object,
 * typically received in a REST request, into a {@link CreateProfileCommand} that can be used
 * in the application's domain layer.
 * </p>
 */
public class CreateProfileCommandFromResourceAssembler {

    /**
     * Converts a CreateProfileResource to a CreateProfileCommand.
     *
     * @param resource the CreateProfileResource containing profile creation details
     * @return a CreateProfileCommand with the information from the resource
     */
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(
                resource.firstName(), resource.lastName(), resource.dni(), resource.email(),
                resource.age(), resource.location(), resource.userId()
        );
    }
}
