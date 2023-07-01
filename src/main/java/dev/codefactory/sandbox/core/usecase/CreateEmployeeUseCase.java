package dev.codefactory.sandbox.core.usecase;

import dev.codefactory.sandbox.core.domain.Employee;

public interface CreateEmployeeUseCase {

    Employee invoke(Employee employee);

    void confirmEmployeeUpdate(Employee employee);
}
