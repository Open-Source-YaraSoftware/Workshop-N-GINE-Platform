package com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.services;

import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Provider;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Role;
import com.yarasoftware.workshopngine.platform.iam.domain.model.valueobjects.AuthProviders;
import com.yarasoftware.workshopngine.platform.iam.domain.model.valueobjects.Roles;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.model.UserInfo;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        if (isUserRegistered(oAuth2User.getName().toString(), provider)) return oAuth2User;
        UserInfo userInfo = processOAuth2User(oAuth2User, provider);
        Role defaultRole = roleRepository.findByName(Roles.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        User user = processOAuth2UserInfo(userInfo);
        user.addRole(defaultRole);
        userRepository.save(user);
        return oAuth2User;
    }

    private UserInfo processOAuth2User(OAuth2User oAuth2User, String provider) {
        if ("github".equals(provider)) {
            return UserInfo.builder()
                    .name(oAuth2User.getAttribute("name"))
                    .firstName(oAuth2User.getAttribute("login"))
                    .lastName(oAuth2User.getAttribute("login"))
                    .email(oAuth2User.getAttribute("email"))
                    .imageUrl(oAuth2User.getAttribute("avatar_url"))
                    .provider("github")
                    .providerId(oAuth2User.getName().toString())
                    .nickname(oAuth2User.getAttribute("login"))
                    .attributes(oAuth2User.getAttributes())
                    .build();
        }
        throw new OAuth2AuthenticationException("Provider not supported: " + provider);
    }

    private User processOAuth2UserInfo(UserInfo userInfo) {
        User user = new User();
        user.setEmail(userInfo.getEmail());
        user.updateProviderUserId(userInfo.getProviderId());
        if(userInfo.getProvider().equals("github")) {
            user.useGithubAsExternalAuthProvider();
        }
        user.activateAccount();
        return user;
    }

    private boolean isUserRegistered(String providerUserId, String providerName) {
        Provider provider = new Provider();
        provider.setProviderUserId(providerUserId);
        if (providerName.equals("github")) {
            provider.setProviderName(AuthProviders.GITHUB);
        } else {
            throw new OAuth2AuthenticationException("Provider not supported: " + providerName);
        }
        return userRepository.existsByAuthProvider(provider);
    }
}
