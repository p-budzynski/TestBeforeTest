package pl.kurs.task2.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.kurs.task2.dto.VehicleCsvDto;

import static org.assertj.core.api.Assertions.assertThat;

public class VehicleValidatorTest {
    private VehicleValidator vehicleValidator;

    @BeforeEach
    void setup() {
        vehicleValidator = new VehicleValidator();
    }

    @ParameterizedTest
    @CsvSource({
            ", Brand, Model, 4",
            "Type, , Model, 4",
            "Type, Brand, , 4",
            "Type, Brand, Model, ",
            "Type, Brand, Model, 0",
            "Type, Brand, Model, -4",
            "Type, Brand, Model, abc"
    })
    void shouldReturnFalseForInvalidInputs(String type, String brand, String model, String numberOfSeats) {
        //given
        VehicleCsvDto dto = new VehicleCsvDto(type, brand, model, numberOfSeats);

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
