package dev.codefactory.sandbox.core.usecase

import spock.lang.Specification

class ShippingSpec extends Specification {

    def "MinimalNumberOfPackages"() {

        given:
        int items = 100
        int largePack = 5
        int smallPack = 100

        when:
        int result = Shipping.minimalNumberOfPackages(items, largePack, smallPack)

        then:
        result == 5 + 75
    }

    def "should return -1 when its impossible"() {

        given:
        int items = 100
        int largePack = 0
        int smallPack = 0

        when:
        int result = Shipping.minimalNumberOfPackages(items, largePack, smallPack)

        then:
        result == -1
    }

    def "should return -1 when its impossible"() {

        given:
        int items = 100
        int largePack = 1
        int smallPack = 1

        when:
        int result = Shipping.minimalNumberOfPackages(items, largePack, smallPack)

        then:
        result == -1
    }

    def "should return -1 when its impossible negative"() {

        given:
        int items = 100
        int largePack = -1
        int smallPack = -1

        when:
        int result = Shipping.minimalNumberOfPackages(items, largePack, smallPack)

        then:
        result == -1
    }

    def "should return -1 when its impossible negative only small packs"() {

        given:
        int items = 4
        int largePack = 1
        int smallPack = 4

        when:
        int result = Shipping.minimalNumberOfPackages(items, largePack, smallPack)

        then:
        result == 4
    }

    def "should return 1 when its impossible negative only 1"() {

        given:
        int items = 5
        int largePack = 1
        int smallPack = 5

        when:
        int result = Shipping.minimalNumberOfPackages(items, largePack, smallPack)

        then:
        result == 1
    }
}
