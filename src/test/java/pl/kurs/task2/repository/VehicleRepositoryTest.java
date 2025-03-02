package pl.kurs.task2.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kurs.task2.entity.SportCar;
import pl.kurs.task2.entity.Truck;
import pl.kurs.task2.entity.Vehicle;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class VehicleRepositoryTest {

    @Mock
    private EntityManager entityManagerMock;

    @InjectMocks
    private VehicleRepository vehicleRepository;

    @Captor
    private ArgumentCaptor<Vehicle> vehicleCaptor;

    @Test
    void shouldSaveAllVehicles() {
        //given
        List<Vehicle> vehicleList = Arrays.asList(
                new SportCar("Toyota", "Supra", 2),
                new Truck("Volvo", "FH16", 2));

        //when
        vehicleRepository.saveAll(vehicleList);

        //then
        verify(entityManagerMock, times(2)).persist(vehicleCaptor.capture());
        List<Vehicle> capturedVehicles = vehicleCaptor.getAllValues();
        assertThat(capturedVehicles).hasSize(2);
        assertThat(capturedVehicles).containsExactlyInAnyOrderElementsOf(vehicleList);
    }
}
