package pl.kurs.task2.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.kurs.task2.dto.VehicleCsvDto;

import static org.assertj.core.api.Assertions.assertThat;

public class VehicleValidatorTest {
    private VehicleValidator vehicleValidator;

    @BeforeEach
    void setup() {
        vehicleValidator = new VehicleValidator();
    }

    @Test
    void shouldReturnFalseWhenDtoIsNull() {
        //when then
        assertThat(vehicleValidator.isValid(null)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenTypeIsNull() {
        //given
        VehicleCsvDto dto = new VehicleCsvDto(null, "Brand", "Model", "4");

        //when then
        assertThat(vehicleValidator.isValid(dto)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenBrandIsNull() {
        //given
        VehicleCsvDto dto = new VehicleCsvDto("Type", null, "Model", "4");

        //when then
        assertThat(vehicleValidator.isValid(dto)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenModelIsNull() {
        //given
        VehicleCsvDto dto = new VehicleCsvDto("Type", "Brand", null, "4");

        //when then
        assertThat(vehicleValidator.isValid(dto)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenNumberOfSeatsIsNull() {
        //given
        VehicleCsvDto dto = new VehicleCsvDto("Type", "Brand", "Model", null);

        //when then
        assertThat(vehicleValidator.isValid(dto)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenNumberOfSeatsIsZero() {
        //given
        VehicleCsvDto dto = new VehicleCsvDto("Type", "Brand", "Model", "0");

        //when then
        assertThat(vehicleValidator.isValid(dto)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenNumberOfSeatsIsNegative() {
        //given
        VehicleCsvDto dto = new VehicleCsvDto("Type", "Brand", "Model", "-4");

        //when then
        assertThat(vehicleValidator.isValid(dto)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenNumberOfSeatsIsNotANumber(){
        //given
        VehicleCsvDto dto = new VehicleCsvDto("Type", "Brand", "Model", "abc");

        //when then
        assertThat(vehicleValidator.isValid(dto)).isFalse();
    }

    @Test
    void shouldReturnTrueWhenAllFieldsAreValidAndNumberOfSeatsIsPositive() {
        //given
        VehicleCsvDto dto = new VehicleCsvDto("Type", "Brand", "Model", "4");

        //when then
        assertThat(vehicleValidator.isValid(dto)).isTrue();
    }

}
