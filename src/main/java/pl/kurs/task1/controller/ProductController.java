package pl.kurs.task1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.kurs.task1.dto.ProductDto;
import pl.kurs.task1.dto.ProductDtoMapper;
import pl.kurs.task1.entity.Product;
import pl.kurs.task1.service.ProductService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;

    @PostMapping("")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable("id") Long id) {
        return productDtoMapper.mapToProductDto(productService.getProduct(id));
    }

    @GetMapping("")
    public List<ProductDto> getProducts() {
        return productDtoMapper.mapToProductDtoList(productService.getProducts());
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/database-dump")
    public void exportProductsToJson() throws IOException {
        productService.exportProductsToJson();
    }
}
