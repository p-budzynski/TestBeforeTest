package pl.kurs.task2.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import pl.kurs.task2.dto.VehicleCsvDto;
import pl.kurs.task2.entity.Bus;
import pl.kurs.task2.entity.Vehicle;
import pl.kurs.task2.mapper.VehicleMapper;
import pl.kurs.task2.parrser.VehicleParser;
import pl.kurs.task2.repository.VehicleRepository;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {
    @Mock
    private VehicleRepository vehicleRepositoryMock;

    @Mock
    private VehicleMapper vehicleMapperMock;

    @Mock
    private VehicleParser vehicleParserMock;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void uploadVehicles_shouldParseCsvAndSaveEntities() throws IOException {
        //given
        MultipartFile file = mock(MultipartFile.class);
        List<VehicleCsvDto> vehicleCsvDtos = List.of(new VehicleCsvDto());
        List<Vehicle> vehicles = List.of(new Bus());

        //when
        when(vehicleParserMock.parseCsv(file)).thenReturn(vehicleCsvDtos);
        when(vehicleMapperMock.toEntities(vehicleCsvDtos)).thenReturn(vehicles);

        int result = vehicleService.uploadVehicles(file);

        //then
        verify(vehicleParserMock).parseCsv(file);
        verify(vehicleMapperMock).toEntities(vehicleCsvDtos);
        verify(vehicleRepositoryMock).saveAll(vehicles);
        assertEquals(1, result);
    }
}
