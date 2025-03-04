package pl.kurs.task2.service;

import org.junit.jupiter.api.Test;
import pl.kurs.task2.entity.Truck;
import pl.kurs.task2.entity.Vehicle;

import static org.assertj.core.api.Assertions.assertThat;

public class TruckServiceTest {
    private static final TruckService truckService = new TruckService();

    @Test
    void shouldCreateTruck() {
        //given
        String brand = "Truck Brand";
        String model = "Truck Model";
        int numberOfSeats = 40;

        //when
        Vehicle result = truckService.create(brand, model, numberOfSeats);

        //then
        assertThat(result).isInstanceOf(Truck.class);
        assertThat(result.getBrand()).isEqualTo(brand);
        assertThat(result.getModel()).isEqualTo(model);
        assertThat(result.getNumberOfSeats()).isEqualTo(numberOfSeats);
    }

    @Test
    void shouldReturnCorrectSupportedType() {
        //when
        String result = truckService.getSupportedType();

        //then
        assertThat(result).isEqualTo("truck");
    }
}
