package pl.kurs.task1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.task1.dto.ProductDto;
import pl.kurs.task1.dto.ProductMapper;
import pl.kurs.task1.entity.Product;
import pl.kurs.task1.repository.ProductRepository;
import pl.kurs.task1.validator.ProductValidator;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductValidator productValidator;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    public void addProduct(ProductDto productDto) {
        productValidator.validate(productDto);
        productRepository.save(productMapper.toEntity(productDto));
    }

    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProduct(Long id) {
        return productMapper.toDto(productRepository.findById(id));
    }

    public void updateProduct(Long id, ProductDto productDto) {
        productValidator.validate(productDto);
        Product productUpdated = productRepository.findById(id);
        productUpdated.setName(productDto.getName());
        productUpdated.setPrice(productDto.getPrice());
        productUpdated.setProducer(productDto.getProducer());
        productRepository.save(productUpdated);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
