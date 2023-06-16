package dev.codefactory.sandbox.core.port;

import dev.codefactory.sandbox.core.domain.Employee;

public interface EmployeeEventProducer {

    void sendEmployeeUpdate(Employee employee);
}
