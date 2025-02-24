package pl.kurs.task2.service;

import org.springframework.stereotype.Component;
import pl.kurs.task2.entity.Plane;
import pl.kurs.task2.entity.Vehicle;

@Component
public class PlaneService implements VehicleCreator{
    @Override
    public Vehicle create(String brand, String model, int numberOfSeats) {
        return new Plane(brand, model, numberOfSeats);
    }

    @Override
    public String getSupportedType() {
        return "plane";
    }
}
