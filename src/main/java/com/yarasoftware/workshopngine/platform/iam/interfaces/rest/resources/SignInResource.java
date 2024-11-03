package com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources;

/**
 * SignInResource is a record class that represents the user sign in resource.
 */
public record SignInResource(String username, String password) {
    /**
     * Constructor for the SignInResource class.
     * @param username the user username
     * @param password the
     */
    public SignInResource {
        if (username == null) {
            throw new IllegalArgumentException("username is required");
        }
        if (password == null) {
            throw new IllegalArgumentException("password is required");
        }
    }
}
