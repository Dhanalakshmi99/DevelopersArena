package com.ecommerce.security.audit;

import java.time.LocalDateTime;

public class SecurityEvent {

    private String eventType;
    private String username;
    private String tenantId;
    private String ipAddress;
    private LocalDateTime timestamp;
    private String details;

    public SecurityEvent(String eventType, String username, String tenantId,
                         String ipAddress, String details) {
        this.eventType = eventType;
        this.username = username;
        this.tenantId = tenantId;
        this.ipAddress = ipAddress;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] | " + eventType +
                " | User: " + username +
                " | Tenant: " + tenantId +
                " | IP: " + ipAddress +
                " | Details: " + details;
    }
}
