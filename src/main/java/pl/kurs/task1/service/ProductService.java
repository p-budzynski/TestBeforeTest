package pl.kurs.task1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import pl.kurs.task1.config.FileConfig;
import pl.kurs.task1.entity.Product;
import pl.kurs.task1.repository.ProductRepository;
import pl.kurs.task1.validator.ProductValidator;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductValidator productValidator;
    private final ObjectMapper objectMapper;
    private final FileConfig fileConfig;

    public Product addProduct(Product product) {
        productValidator.validate(product);
        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product product) {
        productValidator.validate(product);
        Product productUpdated = productRepository.findById(id);
        productUpdated.setName(product.getName());
        productUpdated.setPrice(product.getPrice());
        productUpdated.setProducer(product.getProducer());
        return productRepository.save(productUpdated);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void exportProductsToJson() throws IOException {
        List<Product> productList = productRepository.findAll();
        objectMapper.writeValue(new File(fileConfig.getFileJson()), productList);
    }
}
