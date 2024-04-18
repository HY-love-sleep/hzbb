package com.hy.config;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@AllArgsConstructor
public class ApiContext {
    private static final ThreadLocal<Long> CURRENT_TENANT_ID = new ThreadLocal<>();

    public void setCurrentTenantId(Long providerId) {
        CURRENT_TENANT_ID.set(providerId);
    }

    public Long getCurrentTenantId() {
        return CURRENT_TENANT_ID.get();
    }

    public void clearCurrentTenantId() {
        CURRENT_TENANT_ID.remove();
    }
}
