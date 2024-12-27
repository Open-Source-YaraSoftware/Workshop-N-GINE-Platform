package com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.services;

import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Provider;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Role;
import com.yarasoftware.workshopngine.platform.iam.domain.model.valueobjects.AuthProviders;
import com.yarasoftware.workshopngine.platform.iam.domain.model.valueobjects.Roles;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.model.UserAccountInfo;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Provider provider = new Provider(oAuth2User.getName().toString(), userRequest.getClientRegistration().getRegistrationId());
        UserAccountInfo userAccountInfo = processOAuth2User(oAuth2User, provider);
        Optional<User> userOptional = userRepository.findByAuthProvider(provider);
        userOptional.map(existingUser->updateExistingUser(existingUser, userAccountInfo))
                .orElseGet(() -> registerUser(userAccountInfo, provider));
        return oAuth2User;
    }

    private User registerUser(UserAccountInfo userAccountInfo, Provider provider) {
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

    private User updateExistingUser(User existingUser, UserAccountInfo userAccountInfo) {
        existingUser.setUsername(userAccountInfo.getNickname());
        return userRepository.save(existingUser);
    }

    private UserAccountInfo processOAuth2User(OAuth2User oAuth2User, Provider provider) {
        if (provider.getProviderName().equals(AuthProviders.GITHUB)) {
            return UserAccountInfo.builder()
                    .name(oAuth2User.getAttribute("name"))
                    .firstName(oAuth2User.getAttribute("login"))
                    .lastName(oAuth2User.getAttribute("login"))
                    .email(oAuth2User.getAttribute("email"))
                    .imageUrl(oAuth2User.getAttribute("avatar_url"))
                    .provider(provider.getProviderName().toString())
                    .providerId(provider.getProviderUserId())
                    .nickname(oAuth2User.getAttribute("login"))
                    .attributes(oAuth2User.getAttributes())
                    .build();
        }
        throw new OAuth2AuthenticationException("Provider not supported: " + provider);
    }
}
