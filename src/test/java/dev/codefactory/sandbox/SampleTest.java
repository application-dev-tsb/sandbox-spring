package dev.codefactory.sandbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTest {

    @Test
    void stringLowercaseShouldTransformAString() {

        String upperCaseString = "I am UPPER";

        String result = upperCaseString.toLowerCase();

        String expected = "i am upper";
        assertEquals(expected, result);
    }
}
