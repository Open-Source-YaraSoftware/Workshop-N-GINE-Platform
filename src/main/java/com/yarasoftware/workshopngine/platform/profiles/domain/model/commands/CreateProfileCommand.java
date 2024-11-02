package com.yarasoftware.workshopngine.platform.profiles.domain.model.commands;
/**
 * Command to create a new profile.
 *
 * <p>
 * This command object contains the necessary information to create a new profile,
 * including the user's first name, last name, DNI, email, age, location, and user ID.
 * It includes validation logic to ensure all fields meet required constraints.
 * </p>
 *
 * @param firstName the first name of the user, cannot be null or empty
 * @param lastName  the last name of the user, cannot be null or empty
 * @param dni       the DNI of the user, must be exactly 8 digits long
 * @param email     the email address of the user, cannot be null or empty
 * @param age       the age of the user, must be between 1 and 120
 * @param location  the location of the user, cannot be null or empty
 * @param userId    the unique ID associated with the user, must be greater than 0
 */
public record CreateProfileCommand(String firstName, String lastName, int dni, String email, int age, String location, long userId) {
    public CreateProfileCommand {
        if (firstName == null || firstName.isBlank())
            throw new IllegalArgumentException("First name cannot be null or empty");

        if (lastName == null || lastName.isBlank())
            throw new IllegalArgumentException("Last name cannot be null or empty");

        if (dni < 10000000 || dni > 99999999)
            throw new IllegalArgumentException("DNI must be 8 digits long");

        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email cannot be null or empty");

        if (age <= 0 || age > 120)
            throw new IllegalArgumentException("Age must be between 0 and 120");

        if (location == null || location.isBlank())
            throw new IllegalArgumentException("Location cannot be null or empty");

        if (userId <= 0)
            throw new IllegalArgumentException("User ID cannot be less than or equal to 0");
    }
}
