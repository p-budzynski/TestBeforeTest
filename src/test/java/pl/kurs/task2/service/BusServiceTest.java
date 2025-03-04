package pl.kurs.task2.service;

import org.junit.jupiter.api.Test;
import pl.kurs.task2.entity.Bus;
import pl.kurs.task2.entity.Vehicle;

import static org.assertj.core.api.Assertions.assertThat;

public class BusServiceTest {
    private static final BusService busService = new BusService();

    @Test
    void shouldCreateBus() {
        //given
        String brand = "BusBrand";
        String model = "BusModel";
        int numberOfSeats = 40;

        //when
        Vehicle result = busService.create(brand, model, numberOfSeats);

        //then
        assertThat(result).isInstanceOf(Bus.class);
        assertThat(result.getBrand()).isEqualTo(brand);
        assertThat(result.getModel()).isEqualTo(model);
        assertThat(result.getNumberOfSeats()).isEqualTo(numberOfSeats);
    }

    @Test
    void shouldReturnCorrectSupportedType() {
        //when
        String result = busService.getSupportedType();

        //then
        assertThat(result).isEqualTo("bus");
    }
}
