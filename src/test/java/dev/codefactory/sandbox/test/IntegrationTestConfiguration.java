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

    // TODO figure out how to add a custom container to the test context
//    @Bean
//    GenericContainer<?> kafkaSchemaRegistry() {
//        Map<String, String> env = Map.of(
//                "SCHEMA_REGISTRY_HOST_NAME", "schema_registry",
//                "SCHEMA_REGISTRY_KAFKASTORE_CONNECTION_URL", "schema_registry",
//                "SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS", "schema_registry",
//                "SCHEMA_REGISTRY_ACCESS_CONTROL_ALLOW_ORIGIN", "schema_registry"
//        );
//        return new GenericContainer<>(parse("confluentinc/cp-schema-registry:7.4.0")).withEnv(env);
//    }

}
