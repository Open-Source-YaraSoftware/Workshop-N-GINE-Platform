package com.yarasoftware.workshopngine.platform.iam.domain.model.commands;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Role;

/**
 * Command to sign up a new user
 */
public record SignUpCommand(String username, String password, Role role, Long workshopId) {
    /**
     * Constructor
     * @param username The username
     *                 (cannot be null or empty)
     * @param password The password
     *                 (cannot be null or empty)
     * @param role The role
     *                 (cannot be null)
     * @param workshopId The workshop ID
     *                 (cannot be null)
     */
    public SignUpCommand {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        if (workshopId == null) {
            throw new IllegalArgumentException("Workshop ID cannot be null");
        }
    }
}
