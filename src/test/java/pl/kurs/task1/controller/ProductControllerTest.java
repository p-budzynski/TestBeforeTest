package pl.kurs.task1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.kurs.task1.dto.ProductDto;
import pl.kurs.task1.service.ProductService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Mock
    private ProductService productServiceMock;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void shouldAddProduct() throws Exception {
        //given
        ProductDto productDto = new ProductDto(1L, "Test Product", 100.0, "Test Producer");

        //when then
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk());

        verify(productServiceMock).addProduct(eq(productDto));
    }

    @Test
    void shouldReturnProduct() throws Exception {
        //given
        ProductDto productDto = new ProductDto(1L, "Test Product", 100.0, "Test Producer");

        when(productServiceMock.getProduct(1L)).thenReturn(productDto);

        //when then
        mockMvc.perform(get("/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Product"))
                .andExpect(jsonPath("$.price").value(100.0))
                .andExpect(jsonPath("$.producer").value("Test Producer"));
    }

    @Test
    void shouldReturnListOfProducts() throws Exception {
        //given
        ProductDto productDto1 = new ProductDto(1L, "Product 1", 99.9, "Producer 1");
        ProductDto productDto2 = new ProductDto(2L, "Product 2", 199.9, "Producer 2");
        List<ProductDto> productDtoList = Arrays.asList(productDto1, productDto2);

        when(productServiceMock.getProducts()).thenReturn(productDtoList);

        //when then
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Product 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Product 2"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        //given
        ProductDto productDto = new ProductDto(1L, "Updated Product", 149.99, "Updated Producer");

        //when then
        mockMvc.perform(put("/products/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk());

        verify(productServiceMock).updateProduct(eq(1L), eq(productDto));
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        //when then
        mockMvc.perform(delete("/products/{id}", 1L))
                .andExpect(status().isOk());

        verify(productServiceMock).deleteProduct(1L);
    }

}
