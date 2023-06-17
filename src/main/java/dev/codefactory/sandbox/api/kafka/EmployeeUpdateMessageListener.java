package dev.codefactory.sandbox.api.kafka;

import dev.codefactory.sandbox.avro.EmployeeUpdate;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmployeeUpdateMessageListener {

    /**
     * <a href="https://docs.spring.io/spring-kafka/docs/current/reference/html/#kafka-listener-annotation">Guide for Configuring the POJO listener</a>
     * 1. add @EnableKafka
     * 2.
     *
     * @param key message key
     * @param employeeUpdate actual payload containing the employee update
     */
    @KafkaListener(
            topics = "${sandbox.kafka.employee-update.topic}",
            groupId = "${sandbox.kafka.employee-update.group-id}")
    public void listen(
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Payload ConsumerRecord<String, EmployeeUpdate> employeeUpdate
    ) {
        System.out.println("Received Employee Update: [" + key + "] " + employeeUpdate.value());
    }
}
