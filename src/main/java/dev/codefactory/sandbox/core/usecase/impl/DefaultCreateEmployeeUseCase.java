package dev.codefactory.sandbox.core.usecase.impl;

import dev.codefactory.sandbox.core.domain.Employee;
import dev.codefactory.sandbox.core.port.EmployeeDatastore;
import dev.codefactory.sandbox.core.port.EmployeeEventProducer;
import dev.codefactory.sandbox.core.usecase.CreateEmployeeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DefaultCreateEmployeeUseCase implements CreateEmployeeUseCase {

    private final EmployeeDatastore employeeDatastore;
    private final EmployeeEventProducer employeeEventProducer;

    @Override
    public Employee invoke(Employee employee) {
        var newEmployee = employeeDatastore.create(employee);
        employeeEventProducer.sendEmployeeUpdate(newEmployee);
        return newEmployee;
    }

    @Override
    public void confirmEmployeeUpdate(Employee employee) {

    }
}
