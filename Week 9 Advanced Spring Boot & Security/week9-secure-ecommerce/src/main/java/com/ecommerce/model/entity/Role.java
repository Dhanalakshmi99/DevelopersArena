package com.ecommerce.model.entity;

import java.util.HashSet;
import java.util.Set;
import com.ecommerce.model.enums.Permission;

public class Role {

    private Long id;
    private String name; // e.g., ROLE_ADMIN, ROLE_VENDOR, ROLE_CUSTOMER
    private Set<Permission> permissions = new HashSet<>();

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<Permission> getPermissions() { return permissions; }
    public void setPermissions(Set<Permission> permissions) { this.permissions = permissions; }

    public void addPermission(Permission permission) { this.permissions.add(permission); }

    public void removePermission(Permission permission) { this.permissions.remove(permission); }
}
