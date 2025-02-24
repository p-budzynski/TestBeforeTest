package pl.kurs.task2.service;

import pl.kurs.task2.entity.Vehicle;

public interface VehicleCreator {
    Vehicle create(String brand, String model, int numberOfSeats);
    String getSupportedType();
}
