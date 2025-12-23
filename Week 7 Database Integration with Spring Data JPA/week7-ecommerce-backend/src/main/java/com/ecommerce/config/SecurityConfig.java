package com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for API
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/products", "/api/products/**").permitAll()
                
                // Admin endpoints
                .requestMatchers("/api/products/**").hasRole("ADMIN")
                
                // User endpoints (authenticated)
                .requestMatchers("/api/orders/**").authenticated()
                .requestMatchers("/api/users/profile").authenticated()
                
                // Any other endpoint requires authentication
                .anyRequest().authenticated()
            )
            .httpBasic(); // Or configure JWT authentication here

        return http.build();
    }
}
