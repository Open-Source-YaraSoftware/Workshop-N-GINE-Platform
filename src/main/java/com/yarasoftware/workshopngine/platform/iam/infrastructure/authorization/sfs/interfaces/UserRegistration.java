package com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.interfaces;

import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Provider;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.model.UserAccountInfo;

public interface UserRegistration {
    User registerUser(UserAccountInfo userAccountInfo, Provider provider);
    User updateExistingUser(User existingUser, UserAccountInfo userAccountInfo);
}
