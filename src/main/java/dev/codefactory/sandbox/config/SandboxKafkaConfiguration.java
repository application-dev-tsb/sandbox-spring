package dev.codefactory.sandbox.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@EnableConfigurationProperties(SandboxKafkaProperties.class)
@Configuration
public class SandboxKafkaConfiguration {
}
