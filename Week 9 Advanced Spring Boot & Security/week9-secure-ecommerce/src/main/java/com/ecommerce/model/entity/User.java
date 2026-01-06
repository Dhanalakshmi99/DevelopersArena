package com.ecommerce.model.entity;

import com.ecommerce.model.enums.UserStatus;

import java.util.HashSet;
import java.util.Set;

public class User {

    private Long id;
    private String email;
    private String password;
    private String fullName;
    private UserStatus status;
    private Set<Role> roles = new HashSet<>();
    private Tenant tenant;

    public User() {
    }

    public User(Long id, String email, String password, String fullName, UserStatus status, Tenant tenant) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.status = status;
        this.tenant = tenant;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public UserStatus getStatus() { return status; }
    public void setStatus(UserStatus status) { this.status = status; }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public Tenant getTenant() { return tenant; }
    public void setTenant(Tenant tenant) { this.tenant = tenant; }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }
}
