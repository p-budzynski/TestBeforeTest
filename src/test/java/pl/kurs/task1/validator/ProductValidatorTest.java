package pl.kurs.task1.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.kurs.task1.dto.ProductDto;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductValidatorTest {
    private ProductValidator productValidator;

    @BeforeEach
    void setup() {
        productValidator = new ProductValidator();
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        //given
        ProductDto product = new ProductDto(1L,null, 100.0, "Producer");

        //when then
        assertThatThrownBy(() -> productValidator.validate(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product name cannot be empty.");
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        //given
        ProductDto product = new ProductDto(1L,"  ", 100.0, "Producer");

        //when then
        assertThatThrownBy(() -> productValidator.validate(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product name cannot be empty.");
    }

    @Test
    void shouldThrowExceptionWhenPriceIsZero() {
        //given
        ProductDto product = new ProductDto(1L,"Product", 0.0, "Producer");

        //when then
        assertThatThrownBy(() -> productValidator.validate(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product price must be grater than 0.");
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNegative() {
        //given
        ProductDto product = new ProductDto(1L,"Product", -10.0, "Producer");

        //when then
        assertThatThrownBy(() -> productValidator.validate(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product price must be grater than 0.");
    }

    @Test
    void shouldThrowExceptionWhenProducerIsNull() {
        //given
        ProductDto product = new ProductDto(1L,"Product", 100.0, null);

        //when then
        assertThatThrownBy(() -> productValidator.validate(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product producer cannot be empty.");
    }

    @Test
    void shouldThrowExceptionWhenProducerIsEmpty() {
        //given
        ProductDto product = new ProductDto(1L,"Product", 100.0, "   ");

        //when then
        assertThatThrownBy(() -> productValidator.validate(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product producer cannot be empty.");
    }

    @Test
    void shouldPassForValidProduct() {
        //given
        ProductDto product = new ProductDto(1L, "Valid Product", 100.0, "Valid Producer");

        //when then
        assertThatCode(() -> productValidator.validate(product)).doesNotThrowAnyException();
    }
}
