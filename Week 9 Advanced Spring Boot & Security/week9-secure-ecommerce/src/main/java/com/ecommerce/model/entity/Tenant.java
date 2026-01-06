package com.ecommerce.model.entity;

public class Tenant {

    private Long id;
    private String name;
    private String domain; // For subdomain-based multi-tenancy
    private boolean active;

    public Tenant() {
    }

    public Tenant(Long id, String name, String domain, boolean active) {
        this.id = id;
        this.name = name;
        this.domain = domain;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
