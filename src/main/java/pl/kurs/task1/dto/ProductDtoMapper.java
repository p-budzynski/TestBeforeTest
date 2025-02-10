package pl.kurs.task1.dto;

import org.springframework.stereotype.Component;
import pl.kurs.task1.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDtoMapper {

    public ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .producer(product.getProducer())
                .build();
    }

    public List<ProductDto> mapToProductDtoList(List<Product> products) {
        return products.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
