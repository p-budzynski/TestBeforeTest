package pl.kurs.task2.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.task2.dto.VehicleCsvDto;
import pl.kurs.task2.entity.Vehicle;
import pl.kurs.task2.factory.VehicleFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleMapper {
    private final VehicleFactory vehicleFactory;

    public List<Vehicle> toEntities(List<VehicleCsvDto> vehicleCsvDtoList) {
        return vehicleCsvDtoList.stream()
                .map(this::toEntity)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Vehicle toEntity(VehicleCsvDto dto) {
            return vehicleFactory.createVehicle(dto);
    }
}