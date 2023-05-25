package dev.codefactory.sandbox;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class SandboxApplicationTests {

	@Container // TODO there should be a better way
	public static final PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres:15")
			.withDatabaseName("sandbox_test")
			.withUsername("sandbox_test")
			.withPassword("sandbox_test");

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", db::getJdbcUrl);
		registry.add("spring.datasource.username", db::getUsername);
		registry.add("spring.datasource.password", db::getPassword);
	}

	@Test
	void contextLoads() {
	}

}
