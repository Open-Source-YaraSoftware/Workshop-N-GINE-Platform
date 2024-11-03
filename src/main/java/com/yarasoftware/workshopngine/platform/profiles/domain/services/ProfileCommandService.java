package com.yarasoftware.workshopngine.platform.profiles.domain.services;

import com.yarasoftware.workshopngine.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.yarasoftware.workshopngine.platform.profiles.domain.model.commands.UpdateProfileCommand;
/**
 * Service interface for handling profile-related commands.
 *
 * <p>
 * This service defines methods for creating and updating profiles.
 * Implementations of this interface should handle the logic for processing these commands.
 * </p>
 */
public interface ProfileCommandService {
    /**
     * Handles the command to create a new profile.
     *
     * @param command the command containing the details for the new profile
     * @return the ID of the created profile
     */
    Long handle(CreateProfileCommand command);
    /**
     * Handles the command to update an existing profile.
     *
     * @param profileId the ID of the profile to update
     * @param command   the command containing the updated details of the profile
     * @return the ID of the updated profile
     */
    Long handle(Long profileId, UpdateProfileCommand command);
}
