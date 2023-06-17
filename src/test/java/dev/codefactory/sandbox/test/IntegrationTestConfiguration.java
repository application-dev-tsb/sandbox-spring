package dev.codefactory.sandbox.test;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.testcontainers.utility.DockerImageName.parse;

@Configuration
public class IntegrationTestConfiguration {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>(parse("postgres:15.1-alpine"));
    }

    @Bean
    @ServiceConnection
    KafkaContainer kafkaContainer() {
        return new KafkaContainer(parse("confluentinc/cp-kafka:7.4.0"));
    }

}
