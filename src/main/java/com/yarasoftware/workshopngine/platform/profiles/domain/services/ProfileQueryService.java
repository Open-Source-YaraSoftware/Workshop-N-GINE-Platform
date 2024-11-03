package com.yarasoftware.workshopngine.platform.profiles.domain.services;

import com.yarasoftware.workshopngine.platform.profiles.domain.model.aggregates.Profile;
import com.yarasoftware.workshopngine.platform.profiles.domain.model.queries.GetProfileByDniQuery;
import com.yarasoftware.workshopngine.platform.profiles.domain.model.queries.GetProfileByIdQuery;
import com.yarasoftware.workshopngine.platform.profiles.domain.model.queries.GetProfileByUserIdQuery;

import java.util.Optional;

/**
 * Service interface for handling profile-related queries.
 *
 * <p>
 * This service defines methods for retrieving profiles based on different criteria,
 * such as profile ID, DNI, or associated User ID.
 * Implementations of this interface should handle the logic for processing these queries.
 * </p>
 */
public interface ProfileQueryService {

    /**
     * Retrieves a profile by its unique ID.
     *
     * @param query the query containing the ID of the profile to retrieve
     * @return an Optional containing the Profile if found, or an empty Optional if not found
     */
    Optional<Profile> handle(GetProfileByIdQuery query);

    /**
     * Retrieves a profile by its DNI.
     *
     * @param query the query containing the DNI of the profile to retrieve
     * @return an Optional containing the Profile if found, or an empty Optional if not found
     */
    Optional<Profile> handle(GetProfileByDniQuery query);

    /**
     * Retrieves a profile by the associated User ID.
     *
     * @param query the query containing the User ID of the profile to retrieve
     * @return an Optional containing the Profile if found, or an empty Optional if not found
     */
    Optional<Profile> handle(GetProfileByUserIdQuery query);
}
