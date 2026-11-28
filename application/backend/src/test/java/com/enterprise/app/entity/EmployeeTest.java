package com.enterprise.app.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EmployeeTest {

    @Test
    void defaultConstructorShouldCreateEmployeeWithNullFields() {
        Employee employee = new Employee();

        assertNull(employee.getId());
        assertNull(employee.getName());
        assertNull(employee.getEmail());
        assertNull(employee.getDepartment());
    }

    @Test
    void constructorAndGettersSettersShouldWork() {
        Employee employee = new Employee(
                "Test Employee",
                "test@example.com",
                "IT"
        );

        employee.setId(1L);

        assertEquals(1L, employee.getId());
        assertEquals("Test Employee", employee.getName());
        assertEquals("test@example.com", employee.getEmail());
        assertEquals("IT", employee.getDepartment());

        employee.setName("Updated Employee");
        employee.setEmail("updated@example.com");
        employee.setDepartment("Engineering");

        assertEquals("Updated Employee", employee.getName());
        assertEquals("updated@example.com", employee.getEmail());
        assertEquals("Engineering", employee.getDepartment());
    }
}
