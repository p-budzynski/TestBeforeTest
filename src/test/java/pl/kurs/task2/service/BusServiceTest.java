package pl.kurs.task2.service;

import org.junit.jupiter.api.Test;
import pl.kurs.task2.entity.Bus;
import pl.kurs.task2.entity.Vehicle;

import static org.assertj.core.api.Assertions.assertThat;

public class BusServiceTest {

    @Test
    void shouldCreateBus() {
        //given
        BusService busService = new BusService();
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
        //given
        BusService busService = new BusService();

        //when
        String result = busService.getSupportedType();

        //then
        assertThat(result).isEqualTo("bus");
    }
}
