package com.enterprise.app.service;

import com.enterprise.app.entity.Employee;
import com.enterprise.app.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Test
    void getAllShouldReturnEmployees() {
        EmployeeRepository repository = mock(EmployeeRepository.class);
        EmployeeService service = new EmployeeService(repository);

        Employee employee = new Employee();
        employee.setName("Test Employee");

        when(repository.findAll()).thenReturn(List.of(employee));

        List<Employee> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("Test Employee", result.get(0).getName());
        verify(repository).findAll();
    }

    @Test
    void getByIdShouldReturnEmployeeWhenFound() {
        EmployeeRepository repository = mock(EmployeeRepository.class);
        EmployeeService service = new EmployeeService(repository);

        Employee employee = new Employee();
        employee.setName("Test Employee");

        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = service.getById(1L);

        assertEquals("Test Employee", result.getName());
        verify(repository).findById(1L);
    }

    @Test
    void getByIdShouldThrowExceptionWhenNotFound() {
        EmployeeRepository repository = mock(EmployeeRepository.class);
        EmployeeService service = new EmployeeService(repository);

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getById(1L));
    }

    @Test
    void createShouldSaveEmployeeWhenEmailDoesNotExist() {
        EmployeeRepository repository = mock(EmployeeRepository.class);
        EmployeeService service = new EmployeeService(repository);

        Employee employee = new Employee();
        employee.setEmail("test@example.com");

        when(repository.existsByEmail("test@example.com")).thenReturn(false);
        when(repository.save(employee)).thenReturn(employee);

        Employee result = service.create(employee);

        assertSame(employee, result);
        verify(repository).save(employee);
    }

    @Test
    void createShouldThrowExceptionWhenEmailAlreadyExists() {
        EmployeeRepository repository = mock(EmployeeRepository.class);
        EmployeeService service = new EmployeeService(repository);

        Employee employee = new Employee();
        employee.setEmail("test@example.com");

        when(repository.existsByEmail("test@example.com")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> service.create(employee));
        verify(repository, never()).save(any(Employee.class));
    }

    @Test
    void updateShouldUpdateEmployeeFields() {
        EmployeeRepository repository = mock(EmployeeRepository.class);
        EmployeeService service = new EmployeeService(repository);

        Employee existing = new Employee();
        existing.setName("Old Name");
        existing.setEmail("old@example.com");
        existing.setDepartment("Old Department");

        Employee updated = new Employee();
        updated.setName("New Name");
        updated.setEmail("new@example.com");
        updated.setDepartment("New Department");

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        Employee result = service.update(1L, updated);

        assertEquals("New Name", result.getName());
        assertEquals("new@example.com", result.getEmail());
        assertEquals("New Department", result.getDepartment());

        verify(repository).save(existing);
    }

    @Test
    void deleteShouldFindAndDeleteEmployee() {
        EmployeeRepository repository = mock(EmployeeRepository.class);
        EmployeeService service = new EmployeeService(repository);

        Employee employee = new Employee();

        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        service.delete(1L);

        verify(repository).delete(employee);
    }
}
