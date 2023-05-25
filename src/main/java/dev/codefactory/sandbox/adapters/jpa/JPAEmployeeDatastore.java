package dev.codefactory.sandbox.adapters.jpa;

import dev.codefactory.sandbox.core.domain.Employee;
import dev.codefactory.sandbox.core.port.EmployeeDatastore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class JPAEmployeeDatastore implements EmployeeDatastore {
    @Override
    public Employee create(Employee employee) {
        // TODO implement correctly
        employee.setId(UUID.randomUUID().toString());
        return employee;
    }
}
