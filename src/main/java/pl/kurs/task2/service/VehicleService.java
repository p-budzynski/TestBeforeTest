package pl.kurs.task2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.kurs.task2.dto.VehicleCsvDto;
import pl.kurs.task2.entity.Vehicle;
import pl.kurs.task2.mapper.VehicleMapper;
import pl.kurs.task2.parrser.VehicleParser;
import pl.kurs.task2.repository.VehicleRepository;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final VehicleParser vehicleParser;

    public Integer uploadVehicles(MultipartFile file) throws IOException {
        List<VehicleCsvDto> vehicleCsvDtoList = vehicleParser.parseCsv(file);

        List<Vehicle> vehicles = vehicleMapper.toEntities(vehicleCsvDtoList);

        vehicleRepository.saveAll(vehicles);

        return vehicles.size();
    }
}
