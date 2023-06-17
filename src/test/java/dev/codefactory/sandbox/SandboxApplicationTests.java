package dev.codefactory.sandbox;

import dev.codefactory.sandbox.api.rest.EmployeeRestController;
import dev.codefactory.sandbox.core.usecase.CreateEmployeeUseCase;
import dev.codefactory.sandbox.test.IntegrationTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@SpringBootTest(classes = {IntegrationTestConfiguration.class, SandboxApplication.class})
class SandboxApplicationTests {

	@Autowired
	EmployeeRestController controller;

	@Autowired
	CreateEmployeeUseCase useCase;

	/**
	 * smoke test to check if every layer is wired up correctly
	 */
	@Test
	void contextLoads() {
		assertNotNull(controller);
		assertNotNull(useCase);
	}

}
