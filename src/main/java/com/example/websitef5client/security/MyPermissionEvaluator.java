package com.example.websitef5client.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * @author He
 */
@Configuration
public class MyPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication.getPrincipal().toString().compareToIgnoreCase("anonymousUser") != 0) {
            boolean accessable = false;
            String privilege = targetDomainObject + "-" + permission;

            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (privilege.equalsIgnoreCase(authority.getAuthority())) {
                    accessable = true;
                    break;
                }
            }

            return accessable;
        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
