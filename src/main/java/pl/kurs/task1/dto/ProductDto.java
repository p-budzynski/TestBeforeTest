package pl.kurs.task1.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private String producer;
}
