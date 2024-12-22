package com.yarasoftware.workshopngine.platform.iam.domain.model.commands;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Role;

import java.util.List;

/**
 * Command to sign up a new user
 */
public record SignUpCommand(String username, String password, List<Role> roles) {
    /**
     * Constructor
     * @param username The email
     *                 (cannot be null or empty)
     * @param password The password
     *                 (cannot be null or empty)
     * @param roles The roles
     *                 (cannot be null)
     */
    public SignUpCommand {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (roles.isEmpty()) {
            throw new IllegalArgumentException("Roles cannot be null or empty");
        }
    }
}
