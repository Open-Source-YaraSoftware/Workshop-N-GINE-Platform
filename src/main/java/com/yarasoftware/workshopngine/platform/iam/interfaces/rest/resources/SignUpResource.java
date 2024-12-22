package com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources;

import java.util.List;

/**
 * SignUpResource is a record class that represents the user sign up resource.
 */
public record SignUpResource(String email, String password, List<String> roles) {
    /**
     * Constructor for the SignUpResource class.
     * @param email the user email
     * @param password the user password
     * @param roles the user roles
     */
    public SignUpResource {
        if (email == null) {
            throw new IllegalArgumentException("email is required");
        }
        if (password == null) {
            throw new IllegalArgumentException("password is required");
        }
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("roles is required");
        }
    }
}
