package com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.services;

import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Provider;
import com.yarasoftware.workshopngine.platform.iam.domain.model.valueobjects.AuthProviders;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.interfaces.UserRegistration;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.model.UserAccountInfo;
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
    private final UserRegistration userRegistration;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Provider provider = new Provider(oAuth2User.getName().toString(), userRequest.getClientRegistration().getRegistrationId());
        UserAccountInfo userAccountInfo = processOAuth2User(oAuth2User, provider);
        Optional<User> userOptional = userRepository.findByAuthProvider(provider);
        userOptional.map(existingUser-> userRegistration.updateExistingUser(existingUser, userAccountInfo))
                .orElseGet(() -> userRegistration.registerUser(userAccountInfo, provider));
        return oAuth2User;
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
