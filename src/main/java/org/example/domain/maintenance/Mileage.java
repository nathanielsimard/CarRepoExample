package org.example.domain.maintenance;

public record Mileage(long value, MileageUnit unit) {
    public static Mileage parse(Integer value, MileageUnit unit) {
        if (value < 0) {
            throw InvalidMileageException.negativeValue(value);
        }
        return new Mileage(Integer.toUnsignedLong(value), unit);
    }

    public static Mileage fromKilometer(Integer value) {
        return Mileage.parse(value, MileageUnit.Kilometer);
    }

    public Integer toKilometers() {
        return Math.toIntExact(value);
    }

    public enum MileageUnit {
        Kilometer,
    }
}
