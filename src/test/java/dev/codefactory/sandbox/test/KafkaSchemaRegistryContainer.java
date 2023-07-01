package dev.codefactory.sandbox.test;

import com.github.dockerjava.api.command.InspectContainerResponse;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class KafkaSchemaRegistryContainer extends GenericContainer<KafkaSchemaRegistryContainer> {

    private static final DockerImageName DEFAULT_IMAGE_NAME = DockerImageName.parse("confluentinc/cp-schema-registry");
    private static final int KAFKA_SCHEMA_REGISTRY_PORT = 8081;
    private static final String KAFKA_SCHEMA_REGISTRY_HOST = "schema-registry";

    public KafkaSchemaRegistryContainer(final DockerImageName dockerImageName) {
        super(dockerImageName);
        dockerImageName.assertCompatibleWith(DEFAULT_IMAGE_NAME);
        withEnv("SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS", "PLAINTEXT://kafka:9092");
        withEnv("SCHEMA_REGISTRY_LISTENERS", "http://0.0.0.0:8081");
        withEnv("SCHEMA_REGISTRY_HOST_NAME", KAFKA_SCHEMA_REGISTRY_HOST);
        withNetworkAliases(KAFKA_SCHEMA_REGISTRY_HOST);
        withExposedPorts(KAFKA_SCHEMA_REGISTRY_PORT);
    }

    public String getKafkaSchemaRegistryHost() {
        return "http://localhost:%s".formatted(getMappedPort(KAFKA_SCHEMA_REGISTRY_PORT));
    }

    @Override
    protected void containerIsStarted(InspectContainerResponse containerInfo) {
        System.setProperty("spring.kafka.properties.schema.registry.url", getKafkaSchemaRegistryHost());
        System.setProperty("spring.kafka.producer.properties.schema.registry.url", getKafkaSchemaRegistryHost());
        System.setProperty("spring.kafka.consumer.properties.schema.registry.url", getKafkaSchemaRegistryHost());
    }
}
