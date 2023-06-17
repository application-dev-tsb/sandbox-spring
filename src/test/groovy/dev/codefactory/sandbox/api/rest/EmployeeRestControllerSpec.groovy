package dev.codefactory.sandbox.api.rest

import dev.codefactory.sandbox.core.domain.Employee
import dev.codefactory.sandbox.core.usecase.CreateEmployeeUseCase
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@WebMvcTest
class EmployeeRestControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @SpringBean
    CreateEmployeeUseCase mockUseCase = Mock()

    def "should convert the POST request"() {
        given:
        String body = """
            {
                "name": "John Doe"
            }
        """

        and:
        Employee expected = Employee.builder().name("John Doe").build()

        when:
        mockMvc.perform {
            post("/employees")
                    .contentType("application/json")
                    .content(body)
                    .buildRequest(it)
        }

        then:
        1 * mockUseCase.invoke(expected)
    }
}
