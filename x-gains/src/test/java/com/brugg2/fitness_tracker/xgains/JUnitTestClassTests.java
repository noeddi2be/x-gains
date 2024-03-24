package com.brugg2.fitness_tracker.xgains;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JUnitTestClassTests {

    @Test
    void testAddNumbers() {
        JUnitTestClass testClass = new JUnitTestClass();
        int result = testClass.addNumbers(4, 5);
        assertEquals(9, result);
    }
}
