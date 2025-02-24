package pl.kurs.task2.service;

import org.springframework.stereotype.Component;
import pl.kurs.task2.entity.Truck;
import pl.kurs.task2.entity.Vehicle;

@Component
public class TruckService implements VehicleCreator {
    @Override
    public Vehicle create(String brand, String model, int numberOfSeats) {
        return new Truck(brand, model, numberOfSeats);
    }

    @Override
    public String getSupportedType() {
        return "truck";
    }
}
