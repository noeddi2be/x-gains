package com.brugg2.fitness_tracker.xgains.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

class PasswordHashingServiceTest {

    @Test
    void saltShouldReturnDifferentRandomNumberEachTime() {
        int salt1 = PasswordHashingService.setSalt();
        int salt2 = PasswordHashingService.setSalt();

        assertNotEquals(salt1, salt2);
    }

    @Test
    void hashedPasswordShouldCreateSamePasswordTwice() {
        int salt = PasswordHashingService.setSalt();
        String hashedPassword1 = PasswordHashingService.hashPassword("test", salt);
        String hashedPassword2 = PasswordHashingService.hashPassword("test", salt);

        assertEquals(hashedPassword1, hashedPassword2);
    }

}
