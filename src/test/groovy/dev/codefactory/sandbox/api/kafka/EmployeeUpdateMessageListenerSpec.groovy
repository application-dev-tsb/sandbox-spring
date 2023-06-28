package dev.codefactory.sandbox.api.kafka


import dev.codefactory.sandbox.test.IntegrationTestConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = IntegrationTestConfiguration.class)
class EmployeeUpdateMessageListenerSpec extends Specification {

    @Autowired
    EmployeeUpdateMessageListener listener

    def 'should be wired'() {
        expect:
        listener
    }
}
