package pl.kurs.task1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.kurs.task1.dto.ProductDto;
import pl.kurs.task1.entity.Product;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
}
