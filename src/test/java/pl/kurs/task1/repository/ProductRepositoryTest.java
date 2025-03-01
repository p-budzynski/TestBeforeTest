package pl.kurs.task1.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kurs.task1.entity.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private EntityManager entityManagerMock;

    @Mock
    private TypedQuery<Product> typedQueryMock;

    @InjectMocks
    private ProductRepository productRepository;

    @Test
    void shouldSaveProduct() {
        //given
        Product product = new Product("Product Test", 2500.0, "Producer Test");

        //when
        productRepository.save(product);

        //then
        verify(entityManagerMock).persist(product);
        verify(entityManagerMock, never()).merge(any(Product.class));
    }

    @Test
    void shouldUpdateProduct() {
        //given
        Product product = new Product("Product Test", 2500.0, "Producer Test");
        product.setId(1L);

        //when
        productRepository.save(product);

        //then
        verify(entityManagerMock).merge(product);
        verify(entityManagerMock, never()).persist(any(Product.class));
    }

    @Test
    void shouldReturnProductWhenProductExists() {
        //given
        Product product = new Product("Product Test", 2500.0, "Producer Test");
        product.setId(1L);

        //when
        when(entityManagerMock.find(Product.class, 1L)).thenReturn(product);

        Product foundProduct = productRepository.findById(1L);

        //then
        assertThat(foundProduct).isEqualTo(product);
        verify(entityManagerMock).find(Product.class, 1L);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        //when
        when(entityManagerMock.find(Product.class, 999L)).thenReturn(null);

        //then
        assertThatThrownBy(() -> productRepository.findById(999L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Product ID 999 not found.");

        verify(entityManagerMock).find(Product.class, 999L);
    }

    @Test
    void shouldReturnAllProductsWhenProductsExists() {
        //given
        List<Product> productList = Arrays.asList(
                new Product("Product 1 Test", 100.0, "Producer 1 Test"),
                new Product("Product 2 Test", 200.0, "Producer 2 Test"));

        //when
        when(entityManagerMock.createQuery("SELECT p FROM Product p", Product.class))
                .thenReturn(typedQueryMock);
        when(typedQueryMock.getResultList()).thenReturn(productList);

        List<Product> foundProducts = productRepository.findAll();

        //then
        assertThat(foundProducts).hasSize(2);
        assertThat(foundProducts).containsExactlyElementsOf(productList);
        verify(entityManagerMock).createQuery("SELECT p FROM Product p", Product.class);
        verify(typedQueryMock).getResultList();
    }

    @Test
    void shouldThrowExceptionForReturnAllProductsWhenNoProductsExist() {
        //when
        when(entityManagerMock.createQuery("SELECT p FROM Product p", Product.class))
                .thenReturn(typedQueryMock);
        when(typedQueryMock.getResultList()).thenReturn(new ArrayList<>());

        //then
        assertThatThrownBy(() -> productRepository.findAll())
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("No products in the database.");

        verify(entityManagerMock).createQuery("SELECT p FROM Product p", Product.class);
        verify(typedQueryMock).getResultList();
    }

    @Test
    void shouldDeleteProductByIdWhenProductExists() {
        //given
        Product product = new Product("Product Test", 2500.0, "Producer Test");
        product.setId(1L);

        //when
        when(entityManagerMock.find(Product.class, 1L)).thenReturn(product);
        productRepository.deleteById(1L);

        //then
        verify(entityManagerMock).find(Product.class, 1L);
        verify(entityManagerMock).remove(product);
    }

    @Test
    void shouldThrowExceptionForDeleteProductByIdWhenProductNotFound() {
        //when
        when(entityManagerMock.find(Product.class, 999L)).thenReturn(null);

        //then
        assertThatThrownBy(() -> productRepository.deleteById(999L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Product ID 999 not found.");

        verify(entityManagerMock).find(Product.class, 999L);
        verify(entityManagerMock, never()).remove(any(Product.class));
    }

}
