package com.yarasoftware.workshopngine.platform.iam.domain.model.commands;

import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Role;

import java.util.List;

/**
 * Command to create a new user
 * @param username
 * @param password
 * @param roles
 * @param workshopId
 */
public record CreateUserCommand(String username, String password, List<Role> roles, Long workshopId) {
    /**
     * Constructor
     * @param username The username
     *                 (cannot be null or empty)
     * @param password The password
     *                 (cannot be null or empty)
     * @param roles The roles
     *                 (cannot be null)
     * @param workshopId The workshop ID
     *                 (cannot be null)
     *
     */
    public CreateUserCommand {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("Roles cannot be null or empty");
        }
        if (workshopId == null) {
            throw new IllegalArgumentException("Workshop ID cannot be null");
        }
    }
}
