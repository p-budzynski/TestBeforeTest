package pl.kurs.task2.service;

import org.springframework.stereotype.Component;
import pl.kurs.task2.entity.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class VehicleCreationService {
    private final Map<String, VehicleCreator> creators = new HashMap<>();

    public VehicleCreationService(List<VehicleCreator> creators) {
        for (VehicleCreator creator : creators) {
            this.creators.put(creator.getSupportedType().toLowerCase(), creator);
        }
    }

    public Vehicle createVehicle(String type, String brand, String model, int numberOfSeats) {
        VehicleCreator creator = creators.get(type.toLowerCase());
        if (creator == null) {
            System.err.println("Unsupported vehicle type: " + type);
            return null;
        }
        return creator.create(brand, model, numberOfSeats);
    }
}
