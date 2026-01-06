package com.ecommerce.security.oauth2;

import com.ecommerce.user.User;
import com.ecommerce.user.UserRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2User oAuth2User = new DefaultOAuth2User(Collections.emptyList(), Map.of(), "id");

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = null;

        if ("google".equalsIgnoreCase(registrationId)) {
            email = (String) attributes.get("email");
        } else if ("github".equalsIgnoreCase(registrationId)) {
            email = (String) attributes.get("email"); // GitHub email may need extra API call
        }

        if (email == null) {
            throw new RuntimeException("Email not found from OAuth2 provider");
        }

        // Check if user exists, else create
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setRoles(Collections.singleton("ROLE_CUSTOMER"));
            userRepository.save(user);
        }

        return new DefaultOAuth2User(user.getAuthorities(), attributes, "email");
    }
}
