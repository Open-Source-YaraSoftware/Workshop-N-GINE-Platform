package com.yarasoftware.workshopngine.platform.profiles.domain.model.queries;
/**
 * Query to retrieve a profile by its unique ID.
 *
 * <p>
 * This query object contains the ID of the profile to be retrieved.
 * It includes validation logic to ensure the ID is a positive number.
 * </p>
 *
 * @param profileId the unique ID of the profile, must be a positive number and not null
 */
public record GetProfileByIdQuery(Long profileId) {
    public GetProfileByIdQuery {
        if (profileId == null || profileId < 1)
            throw new IllegalArgumentException("Profile ID must be a positive number");
    }
}