package org.example.infra;

import org.example.domain.maintenance.CarId;
import org.example.infra.persistence.OnDiskJSONPersistence;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OnDiskJSONPersistenceTest {
    static class MyClass {
        public String myString;
        public Integer myInteger;

        public MyClass(String myString, Integer myInteger) {
            this.myString = myString;
            this.myInteger = myInteger;
        }
    }

    @Test
    void canSaveAndLoadJavaObject() throws IOException {
        CarId carId = CarId.generate();
        MyClass myClass = new MyClass(carId.toString(), 150);
        OnDiskJSONPersistence<MyClass> persistence = new OnDiskJSONPersistence<>("/tmp/test");

        persistence.save(carId.toString(), myClass);
        MyClass fromDisk = persistence.load(carId.toString(), MyClass.class);

        assertEquals(myClass.myInteger, fromDisk.myInteger);
        assertEquals(myClass.myString, fromDisk.myString);
    }
}