package com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.configuration;

import com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.services.OAuth2UserServiceImpl;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.services.OIDCUserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class AuthorizationServerConfiguration {
    private final OIDCUserServiceImpl OidcUserServiceImpl;
    private final OAuth2UserServiceImpl oAuth2UserServiceImpl;
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationServerConfiguration.class);

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // CORS default configuration
        http.cors(configurer -> configurer.configurationSource( request -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of("*"));
            cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
            cors.setAllowedHeaders(List.of("*"));
            return cors;
        }));

        // Session Management Configuration
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true));

        // CSRF disabled
        http.csrf(AbstractHttpConfigurer::disable);

        // Identity and Access Management Configuration
        http.authorizeHttpRequests(registry -> registry
                .requestMatchers(
                        "/login",
                        "/error",
                        "/oauth2/**",
                        "/api/v1/authentication/**",
                        "/api/v1/workshops",
                        "/v3/api-docs/**",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/webjars/**")
                .permitAll()
                .anyRequest().authenticated());

        // OAuth2 Login Configuration
        http.oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfo -> {
                    userInfo.oidcUserService(OidcUserServiceImpl);
                    userInfo.userService(oAuth2UserServiceImpl);
                })
                .successHandler(
                        (request, response, authentication) -> {
                            logger.debug("OAuth2 Login Success Handler");
                            response.sendRedirect("/");
                        }
                )
                .failureUrl("/login?error=true")
        );

        // Form Login Configuration
        http.formLogin(login -> login
                .loginProcessingUrl("/api/v1/authentication/sign-in")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true"));

        return http.build();
    }
}
