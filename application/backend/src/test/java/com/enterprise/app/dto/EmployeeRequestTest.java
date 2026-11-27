package com.enterprise.app.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeRequestTest {

    @Test
    void recordConstructorShouldSetValues() {
        EmployeeRequest request = new EmployeeRequest(
                "Test Employee",
                "test@example.com",
                "IT"
        );

        assertEquals("Test Employee", request.name());
        assertEquals("test@example.com", request.email());
        assertEquals("IT", request.department());
    }
}