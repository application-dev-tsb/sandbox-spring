package dev.codefactory.sandbox.core.usecase;

import dev.codefactory.sandbox.core.domain.Employee;
import dev.codefactory.sandbox.core.port.EmployeeDatastore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateEmployeeUseCase {

    private final EmployeeDatastore employeeDatastore;

    public Employee invoke(Employee employee) {
        return employeeDatastore.create(employee);
    }
}
