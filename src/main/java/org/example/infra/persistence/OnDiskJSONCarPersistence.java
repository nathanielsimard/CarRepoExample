package org.example.infra.persistence;

import org.example.domain.maintenance.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Year;
import java.util.List;

public class OnDiskJSONCarPersistence {
    private static final String CAR_DIRECTORY_NAME = "car";

    private final OnDiskJSONPersistence<JSONCarDTO> persistence;

    public OnDiskJSONCarPersistence(Path databaseRoot) {
        this.persistence = new OnDiskJSONPersistence<>(Paths.get(databaseRoot.toString(), CAR_DIRECTORY_NAME).toString());
    }

    public void saveFromCar(Car car) {
        JSONCarDTO dto = JSONCarDTO.fromCar(car);

        try {
            persistence.save(dto.id, dto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Car load(CarId carId, List<Wheel> wheels, List<OilChange> oilChanges) {
        try {
            JSONCarDTO carDTO = persistence.load(carId.toString(), JSONCarDTO.class);
            return carDTO.toCar(wheels, oilChanges);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final class JSONCarDTO {
        String serial_number;
        String brand;
        String model;
        String year;
        Integer mileage;
        String id;

        private JSONCarDTO(String serial_number, String brand, String model, String year, Integer mileage, String id) {
            this.serial_number = serial_number;
            this.brand = brand;
            this.model = model;
            this.year = year;
            this.mileage = mileage;
            this.id = id;
        }

        static JSONCarDTO fromCar(Car car) {
            return new JSONCarDTO(car.getSerialNumber().toString(), car.getBrand(), car.getModel(), car.getYear().toString(), car.getMileage().toKilometers(), car.getId().toString());
        }

        Car toCar(List<Wheel> wheels, List<OilChange> oilChanges) {
            return new Car(SerialNumber.fromString(serial_number), brand, model, Year.parse(year), Mileage.fromKilometer(mileage), CarId.fromString(id), wheels, oilChanges);
        }
    }
}