package dev.codefactory.sandbox.adapters.kafka

import dev.codefactory.sandbox.SandboxApplication
import dev.codefactory.sandbox.core.domain.Employee
import dev.codefactory.sandbox.core.port.EmployeeEventProducer
import dev.codefactory.sandbox.test.IntegrationTestConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

@SpringBootTest(classes = [IntegrationTestConfiguration.class, SandboxApplication.class])
class KafkaEmployeeEventProducerSpec extends Specification {

    @Autowired
    EmployeeEventProducer producer

    @Autowired
    TestConsumer consumer

    PollingConditions waitUntil = new PollingConditions(initialDelay: 0.5, timeout: 5)

    def 'producer should be wired'() {
        expect:
        producer != null

        and:
        producer instanceof KafkaEmployeeEventProducer
    }

    def 'should send message to the topic'() {
        given:
        def event = Employee.builder()
                .id("test0001")
                .name('Test')
                .build()

        when:
        producer.sendEmployeeUpdate(event)

        then:
        waitUntil eventually {
            consumer.hasReceivedEvents()
        }

        and:
        consumer.messages.size() == 1
    }

    @Component
    static class TestConsumer {
        def messages = []

        @KafkaListener(
                topics = '${sandbox.kafka.employee-update.topic}',
                groupId = '${sandbox.kafka.employee-update.group-id}')
        void listen(Object event) {
            messages << event
        }

        boolean hasReceivedEvents() {
            messages.size() > 0
        }
    }
}
