package com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources;

/**
 * SignUpResource is a record class that represents the user sign up resource.
 */
public record SignUpResource(String username, String password, Long roleId, Long workshopId) {
    /**
     * Constructor for the SignUpResource class.
     * @param username the user username
     * @param password the user password
     * @param roleId the role id
     * @param workshopId the workshop id
     */
    public SignUpResource {
        if (username == null) {
            throw new IllegalArgumentException("username is required");
        }
        if (password == null) {
            throw new IllegalArgumentException("password is required");
        }
        if (roleId == null) {
            throw new IllegalArgumentException("roleId is required");
        }
        if (workshopId == null) {
            throw new IllegalArgumentException("workshopId is required");
        }
    }
}
