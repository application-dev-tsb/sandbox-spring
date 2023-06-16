package dev.codefactory.sandbox.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "sandbox.kafka")
public class SandboxKafkaProperties {

    private EventProperties employeeUpdate;

    @Data
    public static class EventProperties {
        private String topic;
        private String groupId;
    }
}
