package com.yarasoftware.workshopngine.platform.profiles.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.profiles.domain.model.aggregates.Profile;
import com.yarasoftware.workshopngine.platform.profiles.interfaces.rest.resources.ProfileResource;

/**
 * Assembler class to convert a Profile entity into a ProfileResource.
 *
 * <p>
 * This class provides a utility method to transform a {@link Profile} entity,
 * typically from the domain layer, into a {@link ProfileResource} object that can be
 * used in REST responses.
 * </p>
 */
public class ProfileResourceFromEntityAssembler {

    /**
     * Converts a Profile entity to a ProfileResource.
     *
     * @param entity the Profile entity containing profile details
     * @return a ProfileResource with the information from the entity
     */
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(
                entity.getId(), entity.getFirstName(), entity.getLastName(),
                entity.getDni(), entity.getEmail(), entity.getAge(),
                entity.getLocation(), entity.getUserId()
        );
    }
}
