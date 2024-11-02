package com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources;

/**
 * AuthenticateUserResource is a record class that represents the user authentication resource.
 */
public record AuthenticateUserResource(Long id, String username, Long roleId, Long workshopId) {
    /**
     * Constructor for the AuthenticateUserResource class.
     * @param id the user id
     * @param username the user username
     * @param roleId the role id
     * @param workshopId the workshop id
     */
    public AuthenticateUserResource {
        if (id == null) {
            throw new IllegalArgumentException("id is required");
        }
        if (username == null) {
            throw new IllegalArgumentException("username is required");
        }
        if (roleId == null) {
            throw new IllegalArgumentException("roleId is required");
        }
        if (workshopId == null) {
            throw new IllegalArgumentException("workshopId is required");
        }
    }
}
