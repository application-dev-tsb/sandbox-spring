package dev.codefactory.sandbox.api.kafka;

import dev.codefactory.sandbox.avro.EmployeeUpdate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmployeeUpdateMessageListener {

    @KafkaListener(topics = "employee-update", groupId = "sandbox")
    public void listen(
            @Header(KafkaHeaders.KEY) String key,
            @Payload EmployeeUpdate employeeUpdate
    ) {
        System.out.println("Received Employee Update: [" + key + "] " + employeeUpdate);
    }
}
