package dev.codefactory.sandbox.adapters.kafka;

import dev.codefactory.sandbox.avro.event.EmployeeUpdate;
import dev.codefactory.sandbox.config.SandboxKafkaProperties;
import dev.codefactory.sandbox.core.domain.Employee;
import dev.codefactory.sandbox.core.port.EmployeeEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class KafkaEmployeeEventProducer implements EmployeeEventProducer {

    private final KafkaTemplate<String, EmployeeUpdate> kafkaTemplate;
    private final SandboxKafkaProperties sandboxKafkaProperties;

    @Override
    public void sendEmployeeUpdate(Employee employee) {
        kafkaTemplate.send(
                sandboxKafkaProperties.getEmployeeUpdate().getTopic(),
                        UUID.randomUUID().toString(),
                        EmployeeUpdate.newBuilder()
                                .setId(employee.getId())
                                .setName(employee.getName())
                                .build()
        );
    }
}
