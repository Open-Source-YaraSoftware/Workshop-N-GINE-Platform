package com.yarasoftware.workshopngine.platform.profiles.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

/**
 * Represents a Profile aggregate in the system.
 *
 * <p>
 * This entity contains personal information about a user, including
 * their name, DNI, email, age, location, and associated user ID.
 * It extends {@link AbstractAggregateRoot} to support domain events.
 * </p>
 * @since v1.0.0
 */
@Entity
@Getter
public class Profile extends AbstractAggregateRoot<Profile> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Digits(integer = 8, fraction = 0)
    private int dni;

    @Email
    private String email;

    @Min(0)
    @Max(120)
    private int age;

    private String location;

    @NotNull
    private Long userId;

    /**
     * Default constructor for JPA.
     */
    public Profile() {}

    /**
     * Constructs a Profile with the specified details.
     *
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param dni       the DNI of the user, must be an 8-digit number
     * @param email     the email address of the user, must be valid
     * @param age       the age of the user, must be between 0 and 120
     * @param location  the location of the user
     * @param userId    the unique ID associated with the user
     */
    public Profile(String firstName, String lastName, int dni, String email, int age, String location, Long userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.age = age;
        this.location = location;
        this.userId = userId;
    }
    /**
     * Updates the profile information with the specified details.
     *
     * @param firstName the new first name of the user
     * @param lastName  the new last name of the user
     * @param dni       the new DNI of the user, must be an 8-digit number
     * @param email     the new email address of the user, must be valid
     * @param age       the new age of the user, must be between 0 and 120
     * @param location  the new location of the user
     */
    public void updateProfileInformation(String firstName, String lastName, int dni, String email, int age, String location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.age = age;
        this.location = location;
    }
}
