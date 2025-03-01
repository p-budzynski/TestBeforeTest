package pl.kurs.task1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private String producer;
}
