package dev.codefactory.sandbox.test;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.Duration;

import static org.testcontainers.utility.DockerImageName.parse;

@Configuration
public class IntegrationTestConfiguration {

    private static final String CONFLUENT_KAFKA_VERSION = "7.4.0";

    @Bean
    Network network() {
        return Network.newNetwork();
    }

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>(parse("postgres:15.1-alpine"));
    }

    @Bean
    @ServiceConnection
    KafkaContainer kafkaContainer() {
        return new KafkaContainer(parse("confluentinc/cp-kafka:" + CONFLUENT_KAFKA_VERSION))
                .withNetwork(network())
                .withNetworkAliases("kafka");
    }

    @Bean
    KafkaSchemaRegistryContainer kafkaSchemaRegistry() {
        return new KafkaSchemaRegistryContainer(parse("confluentinc/cp-schema-registry:" + CONFLUENT_KAFKA_VERSION))
                .dependsOn(kafkaContainer())
                .withStartupTimeout(Duration.ofSeconds(100))
                .withNetwork(network());
    }

}
