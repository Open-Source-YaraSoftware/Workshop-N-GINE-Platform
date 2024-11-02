package com.yarasoftware.workshopngine.platform.profiles.domain.model.queries;
/**
 * Query to retrieve a profile by its DNI.
 *
 * <p>
 * This query object contains the DNI of the profile to be retrieved.
 * It includes validation logic to ensure the DNI is exactly 8 digits.
 * </p>
 *
 * @param dni the DNI of the profile, must be an eight-digit number
 */
public record GetProfileByDniQuery(int dni) {
    public GetProfileByDniQuery {
        if (dni < 10000000 || dni > 99999999)
            throw new IllegalArgumentException("DNI must be an eight digit number");
    }
}
