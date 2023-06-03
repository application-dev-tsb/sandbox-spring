package dev.codefactory.sandbox.api.kafka;

import dev.codefactory.sandbox.avro.EmployeeUpdate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeUpdateMessageListener {

    @KafkaListener(topics = "employee-update", groupId = "sandbox")
    public void listen(EmployeeUpdate employeeUpdate) {
        System.out.println("Received Employee Update: " + employeeUpdate);
    }
}
