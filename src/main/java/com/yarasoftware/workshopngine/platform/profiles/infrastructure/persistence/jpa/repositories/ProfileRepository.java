package com.yarasoftware.workshopngine.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.yarasoftware.workshopngine.platform.profiles.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for accessing and managing Profile entities.
 *
 * <p>
 * This repository provides methods for checking existence and retrieving profiles
 * by unique attributes, such as User ID, DNI, and email. It extends {@link JpaRepository}
 * to provide standard CRUD operations.
 * </p>
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    /**
     * Checks if a profile exists with the specified User ID.
     *
     * @param userId the User ID to check
     * @return true if a profile exists with the given User ID, otherwise false
     */
    Boolean existsByUserId(Long userId);

    /**
     * Checks if a profile exists with the specified DNI.
     *
     * @param dni the DNI to check
     * @return true if a profile exists with the given DNI, otherwise false
     */
    Boolean existsByDni(int dni);

    /**
     * Finds a profile by its DNI.
     *
     * @param dni the DNI of the profile to retrieve
     * @return an Optional containing the Profile if found, or an empty Optional if not found
     */
    Optional<Profile> findByDni(int dni);

    /**
     * Finds a profile by the associated User ID.
     *
     * @param userId the User ID of the profile to retrieve
     * @return an Optional containing the Profile if found, or an empty Optional if not found
     */
    Optional<Profile> findByUserId(Long userId);

    /**
     * Finds a profile by its email address.
     *
     * @param email the email address of the profile to retrieve
     * @return an Optional containing the Profile if found, or an empty Optional if not found
     */
    Optional<Profile> findByEmail(String email);
}
