package com.yarasoftware.workshopngine.platform.iam.domain.model.queries;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Role;

/**
 * Query to get all users by role and workshop ID
 */
public record GetAllUsersByRoleAndWorkshopQuery(Role role, Long workshopId) {
    /**
     * Constructor
     * @param role The role
     *             (cannot be null)
     * @param workshopId The workshop ID
     *             (cannot be null)
     */
    public GetAllUsersByRoleAndWorkshopQuery {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        if (workshopId == null) {
            throw new IllegalArgumentException("Workshop ID cannot be null");
        }
    }
}
