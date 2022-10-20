package org.example.infra;

import org.example.domain.maintenance.*;
import org.example.infra.persistence.OnDiskJSONCarPersistence;
import org.example.infra.persistence.OnDiskJSONOilChangePersistence;
import org.example.infra.persistence.OnDiskJSONWheelPersistence;

import java.nio.file.Path;
import java.util.List;

public class OnDiskJSONCarRepository implements CarRepository {
    private final OnDiskJSONCarPersistence persistenceCar;
    private final OnDiskJSONWheelPersistence persistenceWheel;
    private final OnDiskJSONOilChangePersistence persistenceOilChange;

    public OnDiskJSONCarRepository(Path databaseRoot) {
        this.persistenceCar = new OnDiskJSONCarPersistence(databaseRoot);
        this.persistenceWheel = new OnDiskJSONWheelPersistence(databaseRoot);
        this.persistenceOilChange = new OnDiskJSONOilChangePersistence(databaseRoot);
    }

    @Override
    public void save(Car car) {
        persistenceCar.saveFromCar(car);
        persistenceWheel.saveFromCar(car);
        persistenceOilChange.saveFromCar(car);
    }

    @Override
    public Car findById(CarId carId) {
        List<Wheel> wheels = persistenceWheel.find(carId);
        List<OilChange> oilChanges = persistenceOilChange.find(carId);
        return persistenceCar.load(carId, wheels, oilChanges);
    }
}
