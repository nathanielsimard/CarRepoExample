package org.example.infra.persistence;

import org.example.domain.maintenance.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OnDiskJSONWheelPersistence {

    private static final String WHEEL_DIRECTORY_NAME = "wheel";
    private final OnDiskJSONPersistence<JSONWheelListDTO> persistence;

    public OnDiskJSONWheelPersistence(Path databaseRoot) {
        this.persistence = new OnDiskJSONPersistence<>(Paths.get(databaseRoot.toString(), WHEEL_DIRECTORY_NAME).toString());
    }

    public void saveFromCar(Car car) {
        List<JSONWheelDTO> wheels = car.getWheels()
                .stream()
                .map((wheel -> JSONWheelDTO.fromWheel(wheel, car.getId()))).toList();

        JSONWheelListDTO dto = new JSONWheelListDTO(wheels);

        try {
            persistence.save(car.getId().toString(), dto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Wheel> find(CarId carId) {
        try {
            JSONWheelListDTO dto = persistence.load(carId.toString(), JSONWheelListDTO.class);
            return dto.wheels.stream().map(JSONWheelDTO::toWheel).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final class JSONWheelListDTO {
        List<JSONWheelDTO> wheels;

        private JSONWheelListDTO(List<JSONWheelDTO> wheels) {
            this.wheels = wheels;
        }
    }

    private static final class JSONWheelDTO {
        String serial_number;
        String brand;
        String model;
        String position;
        Integer mileage_installation;
        String car_id;

        JSONWheelDTO(String serial_number, String brand, String model, String position,
                     Integer mileage_installation, String car_id) {
            this.serial_number = serial_number;
            this.brand = brand;
            this.model = model;
            this.position = position;
            this.mileage_installation = mileage_installation;
            this.car_id = car_id;
        }

        static JSONWheelDTO fromWheel(Wheel wheel, CarId carId) {
            return new JSONWheelDTO(wheel.serialNumber().toString(), wheel.brand(), wheel.model(), wheel.position().toString(), wheel.mileageInstallation().toKilometers(), carId.toString())
                    ;
        }

        Wheel toWheel() {
            return new Wheel(SerialNumber.fromString(serial_number), brand, model, WheelPosition.valueOf(position), Mileage.fromKilometer(mileage_installation))
                    ;
        }
    }
}