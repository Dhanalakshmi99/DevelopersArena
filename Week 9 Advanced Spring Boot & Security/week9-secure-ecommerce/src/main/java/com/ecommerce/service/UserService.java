package com.ecommerce.service;

import com.ecommerce.model.dto.JwtResponse;
import com.ecommerce.model.entity.Role;
import com.ecommerce.model.entity.User;
import com.ecommerce.security.jwt.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final JwtTokenProvider jwtTokenProvider;

    // In-memory store for simplicity
    private final List<User> users = new ArrayList<>();

    public UserService(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public User authenticateUser(String email, String password) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public JwtResponse generateJwtForUser(User user) {
        String token = jwtTokenProvider.generateToken(user.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());

        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        return new JwtResponse(token, refreshToken, jwtTokenProvider.getExpirationInMs(),
                user.getId(), user.getEmail(), roles,
                user.getTenant() != null ? String.valueOf(user.getTenant().getId()) : null);
    }

    public JwtResponse refreshJwt(String refreshToken) {
        if (!jwtTokenProvider.validate(refreshToken)) return null;

        String email = jwtTokenProvider.getUsername(refreshToken);
        User user = users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
        if (user == null) return null;

        return generateJwtForUser(user);
    }

    public User findById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
