package dev.codefactory.sandbox;

import dev.codefactory.sandbox.api.rest.EmployeeRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@SpringBootTest
class SandboxApplicationTests {

	@Container
	@ServiceConnection
	public static final PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres:15")
			.withDatabaseName("sandbox_test")
			.withUsername("sandbox_test")
			.withPassword("sandbox_test");

	@Autowired
	EmployeeRestController controller;

	/**
	 * smoke test to check if every layer is wired up correctly
	 */
	@Test
	void contextLoads() {
		assertNotNull(controller);
	}

}
