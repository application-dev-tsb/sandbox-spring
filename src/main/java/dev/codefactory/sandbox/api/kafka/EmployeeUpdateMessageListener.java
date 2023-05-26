package dev.codefactory.sandbox.api.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeUpdateMessageListener {

    @KafkaListener(topics = "employee-update", groupId = "employee")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
