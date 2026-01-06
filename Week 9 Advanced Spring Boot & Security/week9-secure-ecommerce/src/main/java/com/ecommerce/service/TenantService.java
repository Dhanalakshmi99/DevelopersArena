package com.ecommerce.service;

import com.ecommerce.model.entity.Tenant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TenantService {

    private final List<Tenant> tenants = new ArrayList<>();

    public Tenant findById(Long id) {
        return tenants.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Tenant> getAllTenants() {
        return tenants;
    }

    public Tenant createTenant(Tenant tenant) {
        tenants.add(tenant);
        return tenant;
    }
}
