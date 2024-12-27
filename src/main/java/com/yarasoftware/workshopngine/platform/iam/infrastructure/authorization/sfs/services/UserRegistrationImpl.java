package com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.services;

import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Provider;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Role;
import com.yarasoftware.workshopngine.platform.iam.domain.model.valueobjects.Roles;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.interfaces.UserRegistration;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.model.UserAccountInfo;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserRegistrationImpl implements UserRegistration {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public User registerUser(UserAccountInfo userAccountInfo, Provider provider) {
        User user = new User();
        user.setUsername(userAccountInfo.getNickname());
        user.setEmail(userAccountInfo.getEmail());
        user.activateAccount();
        user.setAuthProvider(provider);
        Role defaultRole = roleRepository.findByName(Roles.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        user.addRole(defaultRole);
        return userRepository.save(user);
    }

    @Override
    public User updateExistingUser(User existingUser, UserAccountInfo userAccountInfo) {
        existingUser.setUsername(userAccountInfo.getNickname());
        return userRepository.save(existingUser);
    }
}
