package org.example.domain.maintenance;

import java.util.UUID;

public record CarId(String value) {
    public static CarId fromString(String value) {
        return new CarId(value);
    }

    public static CarId generate() {
        return new CarId(UUID.randomUUID().toString());
    }

    public String toString() {
        return this.value;
    }
}
