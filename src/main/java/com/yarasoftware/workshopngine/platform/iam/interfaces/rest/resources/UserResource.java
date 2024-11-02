package com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources;

/**
 * UserResource is a record class that represents the user resource.
 */
public record UserResource(Long id, String username, Long roleId, Long workshopId, String status) {
    /**
     * Constructor for the UserResource class.
     * @param id the user id
     * @param username the user username
     * @param roleId the role id
     * @param workshopId the workshop id
     * @param status the user status
     */
    public UserResource {
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
        if (status == null) {
            throw new IllegalArgumentException("status is required");
        }
    }
}
