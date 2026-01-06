package com.ecommerce.service;

import com.ecommerce.security.audit.SecurityEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuditService {

    private final List<SecurityEvent> events = new ArrayList<>();

    public void logEvent(SecurityEvent event) {
        events.add(event);
        System.out.println("Audit log: " + event.getEventType() + " for user " + event.getUsername());
    }

    public List<SecurityEvent> getAllEvents() {
        return events;
    }
}
