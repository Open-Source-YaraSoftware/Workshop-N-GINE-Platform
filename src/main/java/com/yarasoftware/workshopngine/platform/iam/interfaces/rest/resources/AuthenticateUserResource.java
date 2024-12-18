package com.yarasoftware.workshopngine.platform.iam.interfaces.rest.resources;

import java.util.List;

/**
 * AuthenticateUserResource is a record class that represents the user authentication resource.
 */
public record AuthenticateUserResource(Long id, String username, List<String> roles, Long workshopId, String token) {
}
