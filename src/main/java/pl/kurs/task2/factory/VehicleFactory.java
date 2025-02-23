package pl.kurs.task2.factory;

import org.reflections.Reflections;
import org.springframework.stereotype.Component;
import pl.kurs.task2.dto.VehicleCsvDto;
import pl.kurs.task2.entity.Vehicle;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class VehicleFactory {
    private static final String PACKAGE_WITH_ENTITIES = "pl.kurs.task2.entity";
    private final Map<String, Constructor<? extends Vehicle>> vehicleConstructors = new HashMap<>();

    public VehicleFactory() {
        scanAndRegisterVehicles();
    }

    public Vehicle createVehicle(VehicleCsvDto dto) {
        Constructor<? extends Vehicle> constructor = vehicleConstructors.get(dto.getType().toLowerCase());
        if (constructor != null) {
            try {
                return constructor.newInstance(dto.getBrand(), dto.getModel(), Integer.parseInt(dto.getNumberOfSeats()));
            } catch (Exception ex) {
                System.err.println("Error creating vehicle of type " + dto.getType() + ": " + ex.getMessage());
            }
        } else {
            System.err.println("Unsupported vehicle type: " + dto.getType());
        }
        return null;
    }

    private void scanAndRegisterVehicles() {
        Reflections reflections = new Reflections(PACKAGE_WITH_ENTITIES);
        Set<Class<? extends Vehicle>> vehicleClasses = reflections.getSubTypesOf(Vehicle.class);

        for (Class<? extends Vehicle> clazz : vehicleClasses) {
            String typeName = clazz.getSimpleName().toLowerCase();
            try {
                Constructor<? extends Vehicle> constructor = clazz.getConstructor(String.class, String.class, int.class);
                vehicleConstructors.put(typeName, constructor);
            } catch (NoSuchMethodException ex) {
                System.err.println("Skipping " + clazz.getSimpleName() + ": no valid constructor found.");
            }
        }
    }
}
