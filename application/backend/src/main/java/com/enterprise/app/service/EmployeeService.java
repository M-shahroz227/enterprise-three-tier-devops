
package com.enterprise.app.service;

import com.enterprise.app.entity.Employee;
import com.enterprise.app.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee create(Employee employee) {
        if (repository.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        return repository.save(employee);
    }

    public Employee update(Long id, Employee updated) {
        Employee employee = getById(id);

        employee.setName(updated.getName());
        employee.setEmail(updated.getEmail());
        employee.setDepartment(updated.getDepartment());

        return repository.save(employee);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
