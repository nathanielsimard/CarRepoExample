package org.example.domain.maintenance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MileageTest {
    static Integer KILOMETERS_POSITIVE = 100;
    static Integer KILOMETERS_NEGATIVE = -100;

    @Test
    void whenCreateWithPositiveInteger_shouldCreateMileage() {
        Mileage mileage = Mileage.fromKilometer(KILOMETERS_POSITIVE);
        assertEquals(KILOMETERS_POSITIVE, mileage.toKilometers());
    }

    @Test
    void whenCreateWithNegativeInteger_shouldThrow() {
        assertThrows(InvalidMileageException.class, () -> Mileage.fromKilometer(KILOMETERS_NEGATIVE));
    }
}