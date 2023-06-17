package dev.codefactory.sandbox

import dev.codefactory.sandbox.api.rest.EmployeeRestController
import dev.codefactory.sandbox.test.IntegrationTestConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = [IntegrationTestConfiguration.class, SandboxApplication.class])
class SandboxApplicationSpec extends Specification {

    @Autowired
    EmployeeRestController controller

    def "should run the application"() {
        expect:
        controller != null
    }
}
