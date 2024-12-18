package com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources;

import java.util.List;

/**
 * UserResource is a record class that represents the user resource.
 */
public record UserResource(Long id, String username, List<String> roles, Long workshopId, String status) {
}
