package com.ecommerce.security.oauth2;

import com.ecommerce.security.jwt.JwtTokenProvider;
import com.ecommerce.user.User;
import com.ecommerce.user.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public OAuth2AuthenticationSuccessHandler(JwtTokenProvider jwtTokenProvider,
                                              UserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String email = authentication.getName(); // OAuth2 principal email
        User user = userRepository.findByEmail(email);

        String tenantId = user.getTenantId() != null ? user.getTenantId() : "default";
        String accessToken = jwtTokenProvider.createAccessToken(authentication, tenantId);
        String refreshToken = jwtTokenProvider.createRefreshToken(authentication, tenantId);

        // Send tokens as JSON
        response.setContentType("application/json");
        response.getWriter().write("{\"accessToken\":\"" + accessToken + "\","
                + "\"refreshToken\":\"" + refreshToken + "\","
                + "\"tokenType\":\"Bearer\"}");
        response.getWriter().flush();
    }
}
