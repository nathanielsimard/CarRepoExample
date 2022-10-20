package org.example.domain.maintenance;

import java.time.Year;
import java.util.List;

public class Car {
    private final SerialNumber serialNumber;
    private final String brand;
    private final String model;
    private final Year year;
    private final Mileage mileage;
    private final CarId carId;

    private final List<Wheel> wheels;
    private final List<OilChange> oilChanges;

    public Car(SerialNumber serialNumber, String brand, String model, Year year, Mileage mileage, CarId carId, List<Wheel> wheels, List<OilChange> oilChanges) {
        this.serialNumber = serialNumber;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.carId = carId;
        this.wheels = wheels;
        this.oilChanges = oilChanges;
    }

    public void changeWheel(Wheel wheel) {
        throw new RuntimeException("Not implemented");
    }

    public void changeOil(OilChange oilChange) {
        throw new RuntimeException("Not implemented");
    }

    public SerialNumber getSerialNumber() {
        return serialNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Year getYear() {
        return year;
    }

    public Mileage getMileage() {
        return mileage;
    }

    public CarId getId() {
        return carId;
    }

    public List<OilChange> getOilChanges() {
        return oilChanges;
    }

    public List<Wheel> getWheels() {
        return wheels;
    }
}
