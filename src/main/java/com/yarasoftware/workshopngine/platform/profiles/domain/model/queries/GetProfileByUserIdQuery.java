package com.yarasoftware.workshopngine.platform.profiles.domain.model.queries;
/**
 * Query to retrieve a profile by its associated User ID.
 *
 * <p>
 * This query object contains the User ID of the profile to be retrieved.
 * It includes validation logic to ensure the User ID is a positive number.
 * </p>
 *
 * @param userId the User ID associated with the profile, must be a positive number and not null
 */
public record GetProfileByUserIdQuery(Long userId) {
    public GetProfileByUserIdQuery {
        if (userId == null || userId < 1)
            throw new IllegalArgumentException("User ID must be a positive number");
    }
}