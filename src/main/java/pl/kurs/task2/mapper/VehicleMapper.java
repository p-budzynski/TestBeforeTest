package pl.kurs.task2.mapper;

import org.springframework.stereotype.Service;
import pl.kurs.task2.dto.VehicleCsvDto;
import pl.kurs.task2.entity.Bus;
import pl.kurs.task2.entity.SportCar;
import pl.kurs.task2.entity.Truck;
import pl.kurs.task2.entity.Vehicle;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VehicleMapper {
    public List<Vehicle> toEntities(List<VehicleCsvDto> vehicleCsvDtoList) {
        return vehicleCsvDtoList.stream()
                .map(this::toEntity)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Vehicle toEntity(VehicleCsvDto dto) {
            return switch (dto.getType().toLowerCase()) {
                case "bus" -> new Bus(dto.getBrand(), dto.getModel(), Integer.parseInt(dto.getNumberOfSeats()));
                case "sportcar" -> new SportCar(dto.getBrand(), dto.getModel(), Integer.parseInt(dto.getNumberOfSeats()));
                case "truck" -> new Truck(dto.getBrand(), dto.getModel(), Integer.parseInt(dto.getNumberOfSeats()));
                default -> {
                    System.err.println("Unsupported vehicle type: " + dto.getType());
                    yield null;
                }
            };
    }
}