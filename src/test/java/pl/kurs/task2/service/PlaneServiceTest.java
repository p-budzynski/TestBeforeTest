package pl.kurs.task2.service;

import org.junit.jupiter.api.Test;
import pl.kurs.task2.entity.Plane;
import pl.kurs.task2.entity.Vehicle;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaneServiceTest {
    private static final PlaneService planeService = new PlaneService();

    @Test
    void shouldCreatePlane() {
        //given
        String brand = "Plane Brand";
        String model = "Plane Model";
        int numberOfSeats = 240;

        //when
        Vehicle result = planeService.create(brand, model, numberOfSeats);

        //then
        assertThat(result).isInstanceOf(Plane.class);
        assertThat(result.getBrand()).isEqualTo(brand);
        assertThat(result.getModel()).isEqualTo(model);
        assertThat(result.getNumberOfSeats()).isEqualTo(numberOfSeats);
    }

    @Test
    void shouldReturnCorrectSupportedType() {
        //when
        String result = planeService.getSupportedType();

        //then
        assertThat(result).isEqualTo("plane");
    }
}
