package com.yarasoftware.workshopngine.platform.profiles.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.profiles.domain.model.aggregates.Profile;
import com.yarasoftware.workshopngine.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.yarasoftware.workshopngine.platform.profiles.domain.model.commands.UpdateProfileCommand;
import com.yarasoftware.workshopngine.platform.profiles.domain.services.ProfileCommandService;
import com.yarasoftware.workshopngine.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

/**
 * Implementation of ProfileCommandService
 *
 * <p>
 * This class is the implementation of the ProfileCommandService interface.
 * It is used to handle commands on the Profile aggregate.
 * </p>
 *
 * @since v1.0.0
 */
@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /**
     * Command handler to create a new profile
     *
     * @param command containing profile details
     * @return profileId
     * @throws IllegalArgumentException if a profile with the given email, user ID, or DNI already exists
     */
    @Override
    public Long handle(CreateProfileCommand command) {
        var profile = profileRepository.findByEmail(command.email());
        if (profile.isPresent())
            throw new IllegalArgumentException("Profile with email " + command.email() + " already exists");
        if (profileRepository.existsByUserId(command.userId())) {
            throw new IllegalArgumentException("Profile with user id " + command.userId() + " already exists");
        }
        if (profileRepository.existsByDni(command.dni())) {
            throw new IllegalArgumentException("Profile with DNI " + command.dni() + " already exists");
        }
        var newProfile = new Profile(
                command.firstName(), command.lastName(),
                command.dni(), command.email(),
                command.age(), command.location(),
                command.userId()
        );
        profileRepository.save(newProfile);
        return newProfile.getId();
    }

    /**
     * Command handler to update an existing profile
     *
     * @param command containing updated profile details
     * @return profileId
     * @throws IllegalArgumentException if the profile with the given ID is not found
     */
    @Override
    public Long handle(Long profileId, UpdateProfileCommand command) {
        var optionalProfile = profileRepository.findById(profileId);
        if (optionalProfile.isEmpty()) {
            throw new IllegalArgumentException("Profile with id " + profileId + " not found");
        }
        var profile = optionalProfile.get();
        profile.updateProfileInformation(
                command.firstName(), command.lastName(),
                command.dni(), command.email(),
                command.age(), command.location()
        );
        profileRepository.save(profile);
        return profile.getId();
    }
}
