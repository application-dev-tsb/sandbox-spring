package dev.codefactory.sandbox.api.rest;

import dev.codefactory.sandbox.core.domain.Employee;
import dev.codefactory.sandbox.core.usecase.CreateEmployeeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("employees")
public class EmployeeRestController {

    private final CreateEmployeeUseCase createEmployeeUseCase;

    @PostMapping
    Employee createEmployee(@RequestBody Employee employee) {
        return createEmployeeUseCase.invoke(employee);
    }
}
