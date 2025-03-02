package pl.kurs.task1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kurs.task1.dto.ProductDto;
import pl.kurs.task1.entity.Product;
import pl.kurs.task1.mapper.ProductMapper;
import pl.kurs.task1.repository.ProductRepository;
import pl.kurs.task1.validator.ProductValidator;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepositoryMock;

    @Mock
    private ProductValidator productValidatorMock;

    @InjectMocks
    private ProductService productServiceMock;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    private ProductDto productDto;
    private Product product;

    @BeforeEach
    void setup() {
        productDto = new ProductDto(1L, "Test ProductDto", 100.0, "Test ProducerDto");
        product = new Product("Test Product", 50.0, "Test Producer");
        product.setId(1L);
    }

    @Test
    void shouldCorrectlyMapDtoToEntity() {
        //when
        productServiceMock.addProduct(productDto);

        //then
        verify(productValidatorMock).validate(productDto);
        verify(productRepositoryMock).save(productCaptor.capture());

        Product capturedProduct = productCaptor.getValue();
        assertThat(capturedProduct.getName()).isEqualTo(productDto.getName());
        assertThat(capturedProduct.getPrice()).isEqualTo(productDto.getPrice());
        assertThat(capturedProduct.getProducer()).isEqualTo(productDto.getProducer());
    }

    @Test
    void shouldCorrectlyMapEntitiesToDtos() {
        //given
        List<Product> productList = Arrays.asList(product);
        when(productRepositoryMock.findAll()).thenReturn(productList);

        //when
        List<ProductDto> result = productServiceMock.getProducts();

        //then
        assertThat(result).isNotNull().hasSize(1);
        ProductDto resultDto = result.get(0);
        assertThat(resultDto.getId()).isEqualTo(product.getId());
        assertThat(resultDto.getName()).isEqualTo(product.getName());
        assertThat(resultDto.getPrice()).isEqualTo(product.getPrice());
        assertThat(resultDto.getProducer()).isEqualTo(product.getProducer());
    }

    @Test
    void shouldReturnProductDtoById() {
        //given
        when(productRepositoryMock.findById(1L)).thenReturn(product);

        //when
        ProductDto result = productServiceMock.getProduct(1L);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(product.getName());
        assertThat(result.getPrice()).isEqualTo(product.getPrice());
        assertThat(result.getProducer()).isEqualTo(product.getProducer());
    }

    @Test
    void shouldValidateAndUpdateProduct() {
        //given
        ProductDto updatedProductDto = new ProductDto(1L, "Updated Product", 199.99, "Updated Producer");

        when(productRepositoryMock.findById(1L)).thenReturn(product);

        //when
        productServiceMock.updateProduct(1L, updatedProductDto);

        //then
        verify(productValidatorMock).validate(updatedProductDto);
        verify(productRepositoryMock).save(productCaptor.capture());

        Product updatedProduct = productCaptor.getValue();
        assertThat(updatedProduct.getName()).isEqualTo(updatedProductDto.getName());
        assertThat(updatedProduct.getPrice()).isEqualTo(updatedProductDto.getPrice());
        assertThat(updatedProduct.getProducer()).isEqualTo(updatedProductDto.getProducer());
    }

    @Test
    void shouldDeleteProductById() {
        //when
        productServiceMock.deleteProduct(1L);

        //then
        verify(productRepositoryMock).deleteById(1L);
    }

}
