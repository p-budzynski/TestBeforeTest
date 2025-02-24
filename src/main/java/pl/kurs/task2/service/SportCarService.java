package pl.kurs.task2.service;

import org.springframework.stereotype.Component;
import pl.kurs.task2.entity.SportCar;
import pl.kurs.task2.entity.Vehicle;

@Component
public class SportCarService implements VehicleCreator {
    @Override
    public Vehicle create(String brand, String model, int numberOfSeats) {
        return new SportCar(brand, model, numberOfSeats);
    }

    @Override
    public String getSupportedType() {
        return "sportcar";
    }
}
