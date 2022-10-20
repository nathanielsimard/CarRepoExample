package org.example.domain.maintenance;

public record Wheel(SerialNumber serialNumber, String brand, String model, WheelPosition position,
                    Mileage mileageInstallation) {
}
