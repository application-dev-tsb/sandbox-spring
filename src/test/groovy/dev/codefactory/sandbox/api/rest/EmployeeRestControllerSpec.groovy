package dev.codefactory.sandbox.api.rest


import dev.codefactory.sandbox.core.usecase.CreateEmployeeUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@WebMvcTest
class EmployeeRestControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @MockBean
    CreateEmployeeUseCase mockUseCase

    def "should convert the POST request"() {
        given:
        String body = """
            {
                "name": "John Doe"
            }
        """

        when:
        String responseBody = mockMvc.perform {
            post("/employees")
                .contentType("application/json")
                .content(body)
        }.andDo { print() }
            .andReturn()

        then:
        1 * mockUseCase.invoke(any())

        and:
        responseBody == "Hello John Doe"
    }
}
