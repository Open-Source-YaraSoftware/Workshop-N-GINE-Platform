package com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources;

/**
 * SignInResource is a record class that represents the user sign in resource.
 */
public record SignInResource(String email, String password) {
    /**
     * Constructor for the SignInResource class.
     * @param email the user email
     * @param password the
     */
    public SignInResource {
        if (email == null) {
            throw new IllegalArgumentException("email is required");
        }
        if (password == null) {
            throw new IllegalArgumentException("password is required");
        }
    }
}
