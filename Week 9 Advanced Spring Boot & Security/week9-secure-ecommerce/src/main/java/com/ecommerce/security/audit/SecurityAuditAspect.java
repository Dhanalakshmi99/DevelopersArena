package com.ecommerce.security.audit;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class SecurityAuditAspect {

    // In-memory audit log (can be replaced with DB)
    private List<SecurityEvent> auditLog = new ArrayList<>();

    // Capture all methods annotated with @AuditSecurity (custom annotation optional)
    @AfterReturning(pointcut = "execution(* com.ecommerce.service.*.*(..))")
    public void logSecurityEvent(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();

        String username = request.getUserPrincipal() != null ?
                request.getUserPrincipal().getName() : "ANONYMOUS";
        String tenantId = request.getHeader("X-Tenant-ID");
        String ip = request.getRemoteAddr();
        String details = joinPoint.getSignature().toShortString();

        SecurityEvent event = new SecurityEvent(
                "METHOD_EXECUTION",
                username,
                tenantId != null ? tenantId : "UNKNOWN",
                ip,
                details
        );

        auditLog.add(event);

        // Optional: print to console
        System.out.println(event.toString());
    }

    public List<SecurityEvent> getAuditLog() {
        return auditLog;
    }
}
