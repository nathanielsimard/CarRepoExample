package org.example.domain.maintenance;

public record SerialNumber(String value) {
    public static SerialNumber fromString(String serialNumber) {
        if (serialNumber.length() < 11) {
            throw InvalidSerialNumber.invalidLength(serialNumber);
        }
        if (serialNumber.length() > 11) {
            throw InvalidSerialNumber.invalidLength(serialNumber);
        }

        return new SerialNumber(serialNumber);
    }

    public String toString() {
        return value;
    }


    public static class InvalidSerialNumber extends RuntimeException {
        private InvalidSerialNumber(String message) {
            super(message);
        }

        protected static InvalidSerialNumber invalidLength(String serialNumber) {
            return new InvalidSerialNumber(
                    String.format(
                            "Invalid serial number %s => expected 11 characters, got %d",
                            serialNumber,
                            serialNumber.length()
                    )
            );
        }
    }
}
