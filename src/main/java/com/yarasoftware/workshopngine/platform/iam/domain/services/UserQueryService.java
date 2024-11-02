package com.yarasoftware.workshopngine.platform.iam.domain.services;

import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.queries.GetAllUsersByRoleAndWorkshopQuery;

import java.util.List;

public interface UserQueryService {
    List<User> handle(GetAllUsersByRoleAndWorkshopQuery query);
}
