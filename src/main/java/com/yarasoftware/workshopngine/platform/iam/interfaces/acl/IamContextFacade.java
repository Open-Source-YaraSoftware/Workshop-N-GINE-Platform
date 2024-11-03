package com.yarasoftware.workshopngine.platform.iam.interfaces.acl;

import java.util.List;

public interface IamContextFacade {

    List<Long> fetchAllUserByWorkshopAndRoleIsClient(Long workshopId);

    List<Long> fetchAllUserByWorkshopAndRoleIsMechanic(Long workshopId);

    List<Long> fetchAllUserByWorkshopAndRoleIsOwner(Long workshopId);

    Long createUser(String username, String password, Long roleId, Long workshopId);

    Long createUserWithRoleMechanic(String username, String password, Long workshopId);

    Long createUserWithRoleClient(String username, String password, Long workshopId);
}
