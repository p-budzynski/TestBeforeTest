package pl.kurs.task1.validator;

import org.springframework.stereotype.Component;
import pl.kurs.task1.dto.ProductDto;

@Component
public class ProductValidator {

    public void validate(ProductDto productDto) {
        if (productDto.getName() == null || productDto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        if (productDto.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be grater than 0.");
        }
        if (productDto.getProducer() == null || productDto.getProducer().trim().isEmpty()) {
            throw new IllegalArgumentException("Product producer cannot be empty.");
        }
    }
}
