package dev.codefactory.sandbox.core.usecase

import spock.lang.Specification

import java.text.SimpleDateFormat

class RentalTimeSpec extends Specification {

    SimpleDateFormat sdf = new SimpleDateFormat("d/M/y H:m")

    def "should conflict when start of the other schedule is same schedule"() {

        given:
        RentalTime a = new RentalTime(sdf.parse("03/05/2020 19:00"), sdf.parse("03/05/2020 20:30"))

        and:
        RentalTime b = new RentalTime(sdf.parse("03/05/2020 19:00"), sdf.parse("03/05/2020 20:30"))

        when:
        boolean result = a.conflictsWith(b)

        then:
        result
    }

    def "should conflict when start of the other schedule is overlapping"() {

        given:
        RentalTime a = new RentalTime(sdf.parse("03/05/2020 19:00"), sdf.parse("03/05/2020 20:30"))

        and:
        RentalTime b = new RentalTime(sdf.parse("03/05/2020 20:00"), sdf.parse("03/05/2020 21:30"))

        when:
        boolean result = a.conflictsWith(b)

        then:
        result
    }

    def "should not conflict when not overlapping"() {

        given:
        RentalTime a = new RentalTime(sdf.parse("03/05/2020 19:00"), sdf.parse("03/05/2020 20:30"))

        and:
        RentalTime b = new RentalTime(sdf.parse("03/05/2020 20:30"), sdf.parse("03/05/2020 21:30"))

        when:
        boolean result = a.conflictsWith(b)

        then:
        !result
    }
}
