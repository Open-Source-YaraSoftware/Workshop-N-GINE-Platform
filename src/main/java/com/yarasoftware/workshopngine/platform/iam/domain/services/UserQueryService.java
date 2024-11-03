package com.yarasoftware.workshopngine.platform.iam.domain.services;

import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.queries.GetAllUsersByWorkshopAndRoleIsClientQuery;
import com.yarasoftware.workshopngine.platform.iam.domain.model.queries.GetAllUsersByWorkshopAndRoleIsMechanicQuery;
import com.yarasoftware.workshopngine.platform.iam.domain.model.queries.GetAllUsersByWorkshopAndRoleIsOwnerQuery;

import java.util.List;

public interface UserQueryService {
    List<User> handle(GetAllUsersByWorkshopAndRoleIsOwnerQuery query);
    List<User> handle(GetAllUsersByWorkshopAndRoleIsClientQuery query);
    List<User> handle(GetAllUsersByWorkshopAndRoleIsMechanicQuery query);
}
