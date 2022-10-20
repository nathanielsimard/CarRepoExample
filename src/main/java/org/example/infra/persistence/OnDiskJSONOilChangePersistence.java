package org.example.infra.persistence;

import org.example.domain.maintenance.Car;
import org.example.domain.maintenance.CarId;
import org.example.domain.maintenance.Mileage;
import org.example.domain.maintenance.OilChange;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OnDiskJSONOilChangePersistence {
    private static final String OIL_CHANGE_DIRECTORY_NAME = "oil_change";

    private final OnDiskJSONPersistence<JSONOilChangeListDTO> persistence;

    public OnDiskJSONOilChangePersistence(Path databaseRoot) {
        this.persistence = new OnDiskJSONPersistence<>(Paths.get(databaseRoot.toString(), OIL_CHANGE_DIRECTORY_NAME).toString());
    }

    public void saveFromCar(Car car) {
        JSONOilChangeListDTO dto = new JSONOilChangeListDTO(car.getOilChanges().stream().map(JSONOilChangeDTO::fromOilChaneg).toList());

        try {
            persistence.save(car.getId().toString(), dto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OilChange> find(CarId carId) {
        try {
            JSONOilChangeListDTO dto = persistence.load(carId.toString(), JSONOilChangeListDTO.class);
            return dto.oil_changes.stream().map(JSONOilChangeDTO::toOilChange).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final class JSONOilChangeListDTO {
        List<JSONOilChangeDTO> oil_changes;

        public JSONOilChangeListDTO(List<JSONOilChangeDTO> oil_changes) {
            this.oil_changes = oil_changes;
        }
    }


    private static final class JSONOilChangeDTO {
        Integer changed_at_mileage;

        private JSONOilChangeDTO(Integer changed_at_mileage) {
            this.changed_at_mileage = changed_at_mileage;
        }

        static JSONOilChangeDTO fromOilChaneg(OilChange oilChange) {
            return new JSONOilChangeDTO(oilChange.changedAtMileage().toKilometers());
        }

        OilChange toOilChange() {
            return new OilChange(Mileage.fromKilometer(changed_at_mileage));
        }

    }
}
