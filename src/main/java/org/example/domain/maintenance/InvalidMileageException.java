package org.example.domain.maintenance;

public class InvalidMileageException extends RuntimeException {
    private InvalidMileageException(String message) {
        super(message);
    }

    protected static InvalidMileageException negativeValue(Integer value) {
        return new InvalidMileageException(String.format("Invalid mileage %s => expected positive value", value));
    }
}
