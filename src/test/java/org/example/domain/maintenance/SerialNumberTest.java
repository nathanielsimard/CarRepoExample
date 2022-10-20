package org.example.domain.maintenance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SerialNumberTest {
    static String SERIAL_NUMBER_LENGTH_11 = "12345678910";
    static String SERIAL_NUMBER_LENGTH_10 = "1234567891";
    static String SERIAL_NUMBER_LENGTH_12 = "123456789101";

    @Test
    void whenCreateWith11Characters_shouldCreateSerialNumber() {
        SerialNumber serialNumber = SerialNumber.fromString(SERIAL_NUMBER_LENGTH_11);
        assertEquals(serialNumber.toString(), SERIAL_NUMBER_LENGTH_11);
    }

    @Test
    void whenCreateWithFewCharacters_shouldThrow() {
        assertThrows(SerialNumber.InvalidSerialNumber.class, () -> SerialNumber.fromString(SERIAL_NUMBER_LENGTH_10));
    }

    @Test
    void whenCreateWithTooMuchCharacters_shouldThrow() {
        assertThrows(SerialNumber.InvalidSerialNumber.class, () -> SerialNumber.fromString(SERIAL_NUMBER_LENGTH_12));
    }
}