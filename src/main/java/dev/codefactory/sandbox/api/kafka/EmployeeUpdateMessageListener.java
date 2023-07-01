package dev.codefactory.sandbox.api.kafka;

import dev.codefactory.sandbox.avro.event.EmployeeUpdate;
import dev.codefactory.sandbox.core.domain.Employee;
import dev.codefactory.sandbox.core.usecase.CreateEmployeeUseCase;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmployeeUpdateMessageListener {

    private final CreateEmployeeUseCase useCase;

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
        EmployeeUpdate value = employeeUpdate.value();
        useCase.confirmEmployeeUpdate(Employee.builder()
                .id(value.getId())
                .name(value.getName())
                .build());
    }
}
