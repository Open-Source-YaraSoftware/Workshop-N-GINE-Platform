package com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.handlers;

import com.yarasoftware.workshopngine.platform.iam.infrastructure.tokens.jwt.BearerTokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationSuccessTokenHandler implements AuthenticationSuccessHandler {
    private final BearerTokenService tokenService;
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        String username = extractUsername(authentication);
        if (username == null) {
            sendErrorResponse(response);
            return;
        }
        String token = tokenService.generateToken(username);
        sendSuccessResponse(response, token);
    }

    private String extractUsername(Authentication authentication) {
        if (authentication.getPrincipal() instanceof OidcUser) {
            return extractFromOidcUser((OidcUser) authentication.getPrincipal());
        }
        if (authentication.getPrincipal() instanceof OAuth2User) {
            return extractFromOAuth2User((OAuth2User) authentication.getPrincipal());
        }
        return null;
    }

    private String extractFromOAuth2User(OAuth2User oauth2User) {
        return oauth2User.getAttribute("login");
    }

    private String extractFromOidcUser(OidcUser oidcUser) {
        return oidcUser.getAttribute("name");
    }

    private void sendSuccessResponse(HttpServletResponse response, String token) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write("{\"token\":\"" + token + "\"}");
    }

    private void sendErrorResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"error\":\"" + "Username not found." + "\"}");
    }
}
