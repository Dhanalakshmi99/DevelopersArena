package com.ecommerce.model.dto;

public class UserProfile {

    private Long id;
    private String email;
    private String fullName;
    private String tenantName;

    public UserProfile() {
    }

    public UserProfile(Long id, String email, String fullName, String tenantName) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.tenantName = tenantName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getTenantName() { return tenantName; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }
}
