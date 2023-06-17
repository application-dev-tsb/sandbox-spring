package dev.codefactory.sandbox.config;

import dev.codefactory.sandbox.avro.EmployeeUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
@EnableConfigurationProperties(SandboxKafkaProperties.class)
@Configuration
public class KafkaProducerConfiguration {

    private final KafkaProperties kafkaProperties;

    @Bean
    KafkaTemplate<String, EmployeeUpdate> employeeUpdateKafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties()));
    }
}
// TODO remove this after writing integration tests
