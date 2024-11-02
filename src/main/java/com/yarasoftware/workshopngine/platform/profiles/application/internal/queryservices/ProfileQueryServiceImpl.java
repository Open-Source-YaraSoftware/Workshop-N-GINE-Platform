package com.yarasoftware.workshopngine.platform.profiles.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.profiles.domain.model.aggregates.Profile;
import com.yarasoftware.workshopngine.platform.profiles.domain.model.queries.GetProfileByDniQuery;
import com.yarasoftware.workshopngine.platform.profiles.domain.model.queries.GetProfileByIdQuery;
import com.yarasoftware.workshopngine.platform.profiles.domain.model.queries.GetProfileByUserIdQuery;
import com.yarasoftware.workshopngine.platform.profiles.domain.services.ProfileQueryService;
import com.yarasoftware.workshopngine.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of ProfileQueryService
 *
 * <p>
 * This class is the implementation of the ProfileQueryService interface.
 * It is used to handle queries on the Profile aggregate.
 * </p>
 *
 * @since v1.0.0
 */
@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final ProfileRepository profileRepository;

    /**
     * Constructs a ProfileQueryServiceImpl with the specified repository.
     *
     * @param profileRepository the repository used to access profile data
     */
    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /**
     * Query handler to retrieve a profile by its unique ID.
     *
     * @param query containing the ID of the profile to retrieve
     * @return an Optional containing the Profile if found, or an empty Optional if not found
     */
    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.profileId());
    }

    /**
     * Query handler to retrieve a profile by its DNI.
     *
     * @param query containing the DNI of the profile to retrieve
     * @return an Optional containing the Profile if found, or an empty Optional if not found
     */
    @Override
    public Optional<Profile> handle(GetProfileByDniQuery query) {
        return profileRepository.findByDni(query.dni());
    }

    /**
     * Query handler to retrieve a profile by the associated User ID.
     *
     * @param query containing the User ID of the profile to retrieve
     * @return an Optional containing the Profile if found, or an empty Optional if not found
     */
    @Override
    public Optional<Profile> handle(GetProfileByUserIdQuery query) {
        return profileRepository.findByUserId(query.userId());
    }
}
