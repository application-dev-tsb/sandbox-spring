package dev.codefactory.sandbox.adapters.kafka;

import dev.codefactory.sandbox.avro.EmployeeUpdate;
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
    @Override
    public void sendEmployeeUpdate(Employee employee) {
        kafkaTemplate.send(
                "employee_update",
                        UUID.randomUUID().toString(),
                        EmployeeUpdate.newBuilder()
                                .setId(employee.getId())
                                .setName(employee.getName())
                                .build()
        );
    }
}
