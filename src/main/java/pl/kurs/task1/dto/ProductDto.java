package pl.kurs.task1.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private String producer;
}
