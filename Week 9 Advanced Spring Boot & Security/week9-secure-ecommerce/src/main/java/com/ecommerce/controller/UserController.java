package com.ecommerce.controller;

import com.ecommerce.model.dto.UserProfile;
import com.ecommerce.model.entity.User;
import com.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserProfile profile = new UserProfile(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getTenant() != null ? user.getTenant().getName() : null
        );
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserProfile>> getAllUsers() {
        List<UserProfile> profiles = userService.getAllUsers().stream()
                .map(user -> new UserProfile(
                        user.getId(),
                        user.getEmail(),
                        user.getFullName(),
                        user.getTenant() != null ? user.getTenant().getName() : null
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(profiles);
    }
}
