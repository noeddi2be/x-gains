package com.brugg2.fitness_tracker.xgains.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class UserTest {

    @Test
    /**
     * Test not fully implemented. Logic how to test not clear.
     */
    void hashedPasswordShouldReturnWhat() {
        User test = new User();
        test.setPassword("test");
        assertEquals(1, 1);
    }

    @Test
    void testSetAccountType() {
        User test = new User();
        test.setAccountType(123456789);
        assertEquals("admin", test.getAccountType());

        test.setAccountType(1);
        assertEquals("user", test.getAccountType());
    }

    @Test
    void testSetPassword() {
        User test = new User();

        boolean condition = test.getPassword() == null;
        assertTrue(condition);

        test.setPassword("test");

        condition = test.getPassword() != null;
        assertTrue(condition);
    }
}
