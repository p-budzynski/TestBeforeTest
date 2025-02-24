package pl.kurs.task2.service;

import org.springframework.stereotype.Component;
import pl.kurs.task2.entity.Bus;
import pl.kurs.task2.entity.Vehicle;

@Component
public class BusService implements VehicleCreator {
    @Override
    public Vehicle create(String brand, String model, int numberOfSeats) {
        return new Bus(brand, model, numberOfSeats);
    }

    @Override
    public String getSupportedType() {
        return "bus";
    }
}
