package com.yarasoftware.workshopngine.platform.iam.domain.model.commands;

/**
 * Command to sign in a user
 */
public record SignInCommand(String username, String password) {
    /**
     * Constructor
     * @param username The username
     *                 (cannot be null or empty)
     * @param password The password
     *                 (cannot be null or empty)
     */
    public SignInCommand {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }
}
