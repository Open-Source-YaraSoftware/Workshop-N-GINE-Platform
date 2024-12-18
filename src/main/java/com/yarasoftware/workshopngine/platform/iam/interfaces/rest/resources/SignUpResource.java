package com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources;

import java.util.List;

/**
 * SignUpResource is a record class that represents the user sign up resource.
 */
public record SignUpResource(String username, String password, List<String> roles, Long workshopId) {
    /**
     * Constructor for the SignUpResource class.
     * @param username the user username
     * @param password the user password
     * @param roles the user roles
     * @param workshopId the workshop id
     */
    public SignUpResource {
        if (username == null) {
            throw new IllegalArgumentException("username is required");
        }
        if (password == null) {
            throw new IllegalArgumentException("password is required");
        }
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("roles is required");
        }
        if (workshopId == null) {
            throw new IllegalArgumentException("workshopId is required");
        }
    }
}
