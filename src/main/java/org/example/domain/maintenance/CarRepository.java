package org.example.domain.maintenance;

public interface CarRepository {
    void save(Car car);
    Car findById(CarId carId);
}
