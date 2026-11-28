package com.enterprise.app.dto;

public record EmployeeRequest(
        String name,
        String email,
        String department
) {
}