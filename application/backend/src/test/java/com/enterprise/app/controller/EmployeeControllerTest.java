package com.enterprise.app.controller;

import com.enterprise.app.dto.EmployeeRequest;
import com.enterprise.app.entity.Employee;
import com.enterprise.app.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Test
    void getAllShouldReturnEmployeesFromService() {
        EmployeeService service = mock(EmployeeService.class);
        EmployeeController controller = new EmployeeController(service);

        Employee employee = new Employee();
        employee.setName("Test Employee");

        when(service.getAll()).thenReturn(List.of(employee));

        List<Employee> result = controller.getAll();

        assertEquals(1, result.size());
        assertEquals("Test Employee", result.get(0).getName());
        verify(service).getAll();
    }

    @Test
    void getByIdShouldReturnEmployeeFromService() {
        EmployeeService service = mock(EmployeeService.class);
        EmployeeController controller = new EmployeeController(service);

        Employee employee = new Employee();
        employee.setName("Test Employee");

        when(service.getById(1L)).thenReturn(employee);

        Employee result = controller.getById(1L);

        assertEquals("Test Employee", result.getName());
        verify(service).getById(1L);
    }

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

    @Test
    void updateShouldConvertRequestAndCallService() {
        EmployeeService service = mock(EmployeeService.class);
        EmployeeController controller = new EmployeeController(service);

        EmployeeRequest request = new EmployeeRequest(
                "Updated Employee",
                "updated@example.com",
                "Engineering"
        );

        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("Updated Employee");
        updatedEmployee.setEmail("updated@example.com");
        updatedEmployee.setDepartment("Engineering");

        when(service.update(eq(1L), any(Employee.class)))
                .thenReturn(updatedEmployee);

        Employee result = controller.update(1L, request);

        assertEquals("Updated Employee", result.getName());
        assertEquals("updated@example.com", result.getEmail());
        assertEquals("Engineering", result.getDepartment());

        ArgumentCaptor<Employee> captor =
                ArgumentCaptor.forClass(Employee.class);

        verify(service).update(eq(1L), captor.capture());

        Employee passedEmployee = captor.getValue();

        assertEquals("Updated Employee", passedEmployee.getName());
        assertEquals("updated@example.com", passedEmployee.getEmail());
        assertEquals("Engineering", passedEmployee.getDepartment());
    }

    @Test
    void deleteShouldCallServiceWithEmployeeId() {
        EmployeeService service = mock(EmployeeService.class);
        EmployeeController controller = new EmployeeController(service);

        controller.delete(1L);

        verify(service).delete(1L);
    }
}
