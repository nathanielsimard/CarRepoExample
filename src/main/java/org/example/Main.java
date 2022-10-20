package org.example;

import org.example.domain.maintenance.*;
import org.example.infra.OnDiskJSONCarRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Year;
import java.util.List;

public class Main {
    private static final Path DB = Path.of("/home/nathaniel/Documents");

    public static void main(String[] args) {
        System.out.println("Starting");

        try {
            Files.createDirectory(DB);
        } catch (IOException ignored) {

        }

        CarRepository carRepository = new OnDiskJSONCarRepository(DB);
        Wheel leftWheel = new Wheel(SerialNumber.fromString("12345678910"), "WheelBrand", "WheelModel", WheelPosition.FrontLeft, Mileage.fromKilometer(0));
        Car car = new Car(SerialNumber.fromString("12345678910"), "MyBrand", "Model", Year.now(), Mileage.fromKilometer(10), CarId.generate(), List.of(leftWheel), List.of());

        carRepository.save(car);
        Car carLoaded = carRepository.findById(car.getId());
        System.out.println(String.format("Car loaded %s", carLoaded.getId()));
    }
}