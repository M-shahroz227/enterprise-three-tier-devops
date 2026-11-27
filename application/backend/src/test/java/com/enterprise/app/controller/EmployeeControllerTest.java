package com.enterprise.app.controller;

import com.enterprise.app.dto.EmployeeRequest;
import com.enterprise.app.entity.Employee;
import com.enterprise.app.service.EmployeeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Test
    void createShouldConvertRequestAndCallService() {
        EmployeeService service = mock(EmployeeService.class);
        EmployeeController controller = new EmployeeController(service);

        EmployeeRequest request = new EmployeeRequest(
                "Test Employee",
                "test@example.com",
                "IT"
        );

        Employee savedEmployee = new Employee();
        savedEmployee.setName("Test Employee");
        savedEmployee.setEmail("test@example.com");
        savedEmployee.setDepartment("IT");

        when(service.create(any(Employee.class))).thenReturn(savedEmployee);

        Employee result = controller.create(request);

        assertEquals("Test Employee", result.getName());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("IT", result.getDepartment());

        verify(service).create(any(Employee.class));
    }
}