package pl.kurs.task2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kurs.task2.entity.Bus;
import pl.kurs.task2.entity.Vehicle;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleCreationServiceTest {

    @Mock
    private VehicleCreator vehicleCreatorMock;

    private VehicleCreationService vehicleCreationService;

    @BeforeEach
    void setup() {
        when(vehicleCreatorMock.getSupportedType()).thenReturn("bus");
        vehicleCreationService = new VehicleCreationService(List.of(vehicleCreatorMock));
    }

    @Test
    void shouldCreateVehicleForBusType() {
        //given
        when(vehicleCreatorMock.create("BusBrand Test", "BusModel Test", 50))
                .thenReturn(new Bus("BusBrand Test", "BusModel Test", 50));

        //when
        Vehicle result = vehicleCreationService.createVehicle("bus", "BusBrand Test", "BusModel Test", 50);

        //then
        assertThat(result).isNotNull()
                .isInstanceOf(Bus.class)
                .extracting("brand", "model", "numberOfSeats")
                .containsExactly("BusBrand Test", "BusModel Test", 50);
    }

    @Test
    void shouldReturnNullForUnsupportedVehicleType() {
        //when
        Vehicle result = vehicleCreationService.createVehicle("truck", "TruckBrand", "TruckModel", 30);

        //then
        assertThat(result).isNull();
    }

}
