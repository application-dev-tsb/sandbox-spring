package dev.codefactory.sandbox

import spock.lang.Specification

class SampleSpec extends Specification {

    def "String.lowercase should transform a string"() {

        given: "a string with uppercase letters"
        String upperCaseString = "I am UPPER"

        when: "lowercased"
        String result = upperCaseString.toLowerCase()

        then: "it should generate a new lowercase string"
        result == "i am upper"
    }
}
