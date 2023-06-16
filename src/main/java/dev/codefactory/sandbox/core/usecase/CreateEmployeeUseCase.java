package dev.codefactory.sandbox.core.usecase;

import dev.codefactory.sandbox.core.domain.Employee;
import dev.codefactory.sandbox.core.port.EmployeeDatastore;
import dev.codefactory.sandbox.core.port.EmployeeEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateEmployeeUseCase {

    private final EmployeeDatastore employeeDatastore;
    private final EmployeeEventProducer employeeEventProducer;

    public Employee invoke(Employee employee) {
        var newEmployee = employeeDatastore.create(employee);
        employeeEventProducer.sendEmployeeUpdate(newEmployee);
        return newEmployee;
    }
}
