package pl.kurs.task2.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kurs.task2.dto.VehicleCsvDto;
import pl.kurs.task2.entity.SportCar;
import pl.kurs.task2.entity.Truck;
import pl.kurs.task2.entity.Vehicle;
import pl.kurs.task2.service.VehicleCreationService;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleMapperTest {

    @Mock
    private VehicleCreationService vehicleCreationServiceMock;

    @InjectMocks
    private VehicleMapper vehicleMapper;

    @Test
    void shouldMapVehicleCsvDtoListToEntities() {
        //given
        VehicleCsvDto dto1 = new VehicleCsvDto("sportcar", "Toyota", "Supra", "2");
        VehicleCsvDto dto2 = new VehicleCsvDto("truck", "Volvo", "FH16", "2");

        Vehicle vehicle1 = new SportCar("Toyota", "Supra", 2);
        Vehicle vehicle2 = new Truck("Volvo", "FH16", 2);

        when(vehicleCreationServiceMock.createVehicle("sportcar", "Toyota", "Supra", 2)).thenReturn(vehicle1);
        when(vehicleCreationServiceMock.createVehicle("truck", "Volvo", "FH16", 2)).thenReturn(vehicle2);

        //when
        List<Vehicle> result = vehicleMapper.toEntities(asList(dto1, dto2));

        //then
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(vehicle1, vehicle2);
    }
}
