package pl.kurs.task2.service;

import org.junit.jupiter.api.Test;
import pl.kurs.task2.entity.SportCar;
import pl.kurs.task2.entity.Vehicle;

import static org.assertj.core.api.Assertions.assertThat;

public class SportCarServiceTest {
    private static final SportCarService sportCarService = new SportCarService();

    @Test
    void shouldCreateSportCar() {
        //given
        String brand = "SportCar Brand";
        String model = "SportCar Model";
        int numberOfSeats = 2;

        //when
        Vehicle result = sportCarService.create(brand, model, numberOfSeats);

        //then
        assertThat(result).isInstanceOf(SportCar.class);
        assertThat(result.getBrand()).isEqualTo(brand);
        assertThat(result.getModel()).isEqualTo(model);
        assertThat(result.getNumberOfSeats()).isEqualTo(numberOfSeats);
    }

    @Test
    void shouldReturnCorrectSupportedType() {
        //when
        String result = sportCarService.getSupportedType();

        //then
        assertThat(result).isEqualTo("sportcar");
    }
}
