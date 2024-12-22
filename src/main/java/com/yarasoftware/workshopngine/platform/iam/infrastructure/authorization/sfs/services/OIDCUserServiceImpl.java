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
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OIDCUserServiceImpl extends OidcUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {
        OidcUser oidcUser = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        if (isUserRegistered(oidcUser.getName(), provider)) return oidcUser;
        UserInfo userInfo = processOIDCUser(oidcUser, provider);
        Role defaultRole = roleRepository.findByName(Roles.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        User user = processOIDCUserInfo(userInfo);
        user.addRole(defaultRole);
        userRepository.save(user);
        return oidcUser;
    }

    private UserInfo processOIDCUser(OidcUser oAuth2User, String provider) {
        if ("google".equals(provider)) {
            return UserInfo.builder()
                    .name(oAuth2User.getAttribute("name"))
                    .firstName(oAuth2User.getAttribute("given_name"))
                    .lastName(oAuth2User.getAttribute("family_name"))
                    .email(oAuth2User.getAttribute("email"))
                    .imageUrl(oAuth2User.getAttribute("picture"))
                    .provider("google")
                    .providerId(oAuth2User.getName())
                    .nickname(oAuth2User.getAttribute("nickname"))
                    .phoneNumber(oAuth2User.getAttribute("phoneNumber"))
                    .birthdate(oAuth2User.getAttribute("birthdate"))
                    .attributes(oAuth2User.getAttributes())
                    .build();
        }
        throw new OAuth2AuthenticationException("Provider not supported: " + provider);
    }

    private User processOIDCUserInfo(UserInfo userInfo) {
        User user = new User();
        user.setEmail(userInfo.getEmail());
        user.updateProviderUserId(userInfo.getProviderId());
        if(userInfo.getProvider().equals("google")) {
            user.useGoogleAsExternalAuthProvider();
        }
        user.activateAccount();
        return user;
    }

    private boolean isUserRegistered(String providerUserId, String providerName) {
        Provider provider = new Provider();
        provider.setProviderUserId(providerUserId);
        if (providerName.equals("google")) {
            provider.setProviderName(AuthProviders.GOOGLE);
        } else {
            throw new OAuth2AuthenticationException("Provider not supported: " + providerName);
        }
        return userRepository.existsByAuthProvider(provider);
    }
}
