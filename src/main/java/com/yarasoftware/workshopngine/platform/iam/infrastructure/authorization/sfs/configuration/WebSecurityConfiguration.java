package com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.configuration;

import com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.handlers.AuthenticationSuccessTokenHandler;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.pipeline.BearerAuthorizationRequestFilter;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.services.OAuth2UserServiceImpl;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.services.OIDCUserServiceImpl;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import com.yarasoftware.workshopngine.platform.iam.infrastructure.tokens.jwt.BearerTokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {
    private final UserDetailsService userDetailsService;
    private final BCryptHashingService hashingService;
    private final BearerTokenService tokenService;
    private final OIDCUserServiceImpl OidcUserServiceImpl;
    private final OAuth2UserServiceImpl oAuth2UserServiceImpl;
    private final BearerAuthorizationRequestFilter bearerAuthorizationRequestFilter;
    private final AuthenticationSuccessTokenHandler authenticationSuccessTokenHandler;

    public WebSecurityConfiguration(
            @Qualifier("defaultUserDetailsService") UserDetailsService userDetailsService,
            BCryptHashingService hashingService, BearerTokenService tokenService, OIDCUserServiceImpl oidcUserServiceImpl, OAuth2UserServiceImpl oAuth2UserServiceImpl, BearerAuthorizationRequestFilter bearerAuthorizationRequestFilter, AuthenticationSuccessTokenHandler authenticationSuccessTokenHandler
    ) {
        this.userDetailsService = userDetailsService;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        OidcUserServiceImpl = oidcUserServiceImpl;
        this.oAuth2UserServiceImpl = oAuth2UserServiceImpl;
        this.bearerAuthorizationRequestFilter = bearerAuthorizationRequestFilter;
        this.authenticationSuccessTokenHandler = authenticationSuccessTokenHandler;
    }

    @Bean
    public BearerAuthorizationRequestFilter authorizationRequestFilter() {
        return new BearerAuthorizationRequestFilter(tokenService, userDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(hashingService);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return hashingService;
    }

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
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

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
                .successHandler(authenticationSuccessTokenHandler)
                .failureUrl("/login?error=true")
        );

        // Provider Configuration
        http.authenticationProvider(authenticationProvider());

        // Filter Configuration for Bearer Token
        http.addFilterBefore(bearerAuthorizationRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
