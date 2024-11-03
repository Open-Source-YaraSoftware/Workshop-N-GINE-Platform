package com.yarasoftware.workshopngine.platform.profiles.application.acl;

import com.yarasoftware.workshopngine.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.yarasoftware.workshopngine.platform.profiles.domain.model.commands.UpdateProfileCommand;
import com.yarasoftware.workshopngine.platform.profiles.domain.services.ProfileCommandService;
import com.yarasoftware.workshopngine.platform.profiles.interfaces.acl.ProfilesContextFacade;

/**
 * Facade class for handling profile-related operations in the context.
 *
 * <p>
 * This facade provides a simplified interface for creating and updating profiles,
 * delegating the operations to the {@link ProfileCommandService}.
 * It abstracts the details of command creation and service invocation, offering
 * a cohesive API for clients.
 * </p>
 */
public class ProfilesContextFacadeImpl implements ProfilesContextFacade {

    private final ProfileCommandService profileCommandService;

    /**
     * Constructs a ProfilesContextFacade with the specified command service.
     *
     * @param profileCommandService the service used to handle profile creation and updates
     */
    public ProfilesContextFacadeImpl(ProfileCommandService profileCommandService) {
        this.profileCommandService = profileCommandService;
    }

    /**
     * Creates a new profile with the specified details.
     *
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param dni       the DNI of the user
     * @param email     the email address of the user
     * @param age       the age of the user
     * @param location  the location of the user
     * @param userId    the unique ID associated with the user
     * @return the ID of the created profile
     */
    public Long createProfile(String firstName, String lastName, int dni, String email, int age, String location, Long userId) {
        var createProfileCommand = new CreateProfileCommand(firstName, lastName, dni, email, age, location, userId);
        return profileCommandService.handle(createProfileCommand);
    }

    /**
     * Updates an existing profile with the specified details.
     *
     * @param profileId the ID of the profile to update
     * @param firstName the new first name of the user
     * @param lastName  the new last name of the user
     * @param dni       the new DNI of the user
     * @param email     the new email address of the user
     * @param age       the new age of the user
     * @param location  the new location of the user
     * @return the ID of the updated profile
     */
    public Long updateProfile(Long profileId, String firstName, String lastName, int dni, String email, int age, String location) {
        var updateProfileCommand = new UpdateProfileCommand(firstName, lastName, dni, email, age, location);
        return profileCommandService.handle(profileId, updateProfileCommand);
    }
}
