package dev.codefactory.sandbox.core.usecase

import spock.lang.Specification

class NodeSpec extends Specification {

    def "should calculate the height of a leaf"() {

        given:
        Node leaf = new Node(null, null)

        when:
        int result = leaf.height()

        then:
        result == 1
    }

    def "should calculate the height of a leaf example case"() {

        given:
        Node leaf1 = new Node(null, null)
        Node leaf2 = new Node(null, null)
        Node node = new Node(leaf1, null)
        Node root = new Node(node, leaf2)

        when:
        int result = root.height()

        then:
        result == 2
    }
}
