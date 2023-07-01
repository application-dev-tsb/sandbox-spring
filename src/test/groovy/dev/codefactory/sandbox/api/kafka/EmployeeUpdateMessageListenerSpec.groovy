package dev.codefactory.sandbox.api.kafka

import dev.codefactory.sandbox.avro.event.EmployeeUpdate
import dev.codefactory.sandbox.config.SandboxKafkaProperties
import dev.codefactory.sandbox.core.domain.Employee
import dev.codefactory.sandbox.core.usecase.CreateEmployeeUseCase
import dev.codefactory.sandbox.test.IntegrationTestConfiguration
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

@SpringBootTest(classes = IntegrationTestConfiguration.class)
class EmployeeUpdateMessageListenerSpec extends Specification {

    @Autowired
    EmployeeUpdateMessageListener listener

    @Autowired
    TestProducer producer

    @SpringBean
    CreateEmployeeUseCase mockUseCase = Mock(CreateEmployeeUseCase)

    PollingConditions waitUntil = new PollingConditions(initialDelay: 0.5, timeout: 5)

    def 'should be wired'() {
        expect:
        listener
    }

    def 'should receive message'() {
        given:
        Employee expected = Employee.builder().id('test0001').name('Test').build()

        and:
        def isCalled = false

        and:
        mockUseCase.confirmEmployeeUpdate(_) >> {
            isCalled = true
        }

        when:
        producer.sendTestEvent(expected, 'test0001')

        then:
        waitUntil.eventually {
            isCalled
        }

        // TODO assert values
    }

    @Component
    static class TestProducer {

        private final KafkaTemplate<String, EmployeeUpdate> kafkaTemplate
        private final SandboxKafkaProperties sandboxKafkaProperties

        TestProducer(KafkaTemplate<String, EmployeeUpdate> kafkaTemplate, SandboxKafkaProperties sandboxKafkaProperties) {
            this.kafkaTemplate = kafkaTemplate
            this.sandboxKafkaProperties = sandboxKafkaProperties
        }

        void sendTestEvent(Employee employee, String key) {
            kafkaTemplate.send(
                    sandboxKafkaProperties.getEmployeeUpdate().getTopic(),
                    key,
                    EmployeeUpdate.newBuilder()
                            .setId(employee.getId())
                            .setName(employee.getName())
                            .build()
            );
        }
    }
}
