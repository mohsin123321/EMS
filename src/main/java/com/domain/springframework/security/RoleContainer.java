package com.domain.springframework.security;

import org.springframework.stereotype.Component;

@Component("Role")
public class RoleContainer {
    public static final String ADMIN = Role.ADMIN.toString();
    public static final String EMPLOYEE = Role.EMPLOYEE.toString();
}
