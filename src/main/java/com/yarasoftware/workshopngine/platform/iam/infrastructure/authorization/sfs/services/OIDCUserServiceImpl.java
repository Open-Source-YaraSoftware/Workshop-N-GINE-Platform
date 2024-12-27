package com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.services;

import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Provider;
import com.yarasoftware.workshopngine.platform.iam.domain.model.valueobjects.AuthProviders;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.interfaces.UserRegistration;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.model.UserAccountInfo;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OIDCUserServiceImpl extends OidcUserService {
    private final UserRepository userRepository;
    private final UserRegistration userRegistration;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {
        OidcUser oidcUser = super.loadUser(userRequest);
        Provider provider = new Provider(oidcUser.getName().toString(), userRequest.getClientRegistration().getRegistrationId());
        UserAccountInfo userAccountInfo = processOIDCUser(oidcUser, provider);
        Optional<User> userOptional = userRepository.findByAuthProvider(provider);
        userOptional.map(existingUser-> userRegistration.updateExistingUser(existingUser, userAccountInfo))
                .orElseGet(() -> userRegistration.registerUser(userAccountInfo, provider));
        return oidcUser;
    }

    private UserAccountInfo processOIDCUser(OidcUser oAuth2User, Provider provider) {
        if (provider.getProviderName().equals(AuthProviders.GOOGLE)) {
            return UserAccountInfo.builder()
                    .name(oAuth2User.getAttribute("name"))
                    .firstName(oAuth2User.getAttribute("given_name"))
                    .lastName(oAuth2User.getAttribute("family_name"))
                    .email(oAuth2User.getAttribute("email"))
                    .imageUrl(oAuth2User.getAttribute("picture"))
                    .provider(provider.getProviderName().toString())
                    .providerId(provider.getProviderUserId())
                    .nickname(oAuth2User.getAttribute("name"))
                    .phoneNumber(oAuth2User.getAttribute("phoneNumber"))
                    .birthdate(oAuth2User.getAttribute("birthdate"))
                    .attributes(oAuth2User.getAttributes())
                    .build();
        }
        throw new OAuth2AuthenticationException("Provider not supported: " + provider);
    }
}
