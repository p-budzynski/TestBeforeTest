package pl.kurs.task1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kurs.task1.config.FileConfig;
import pl.kurs.task1.entity.Product;
import pl.kurs.task1.repository.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductExportToJsonServiceTest {

    @Mock
    private ProductRepository productRepositoryMock;

    @Mock
    private FileConfig fileConfigMock;

    @Mock
    private ObjectMapper objectMapperMock;

    @InjectMocks
    private ProductExportToJsonService exportToJsonService;

    private final String EXPORT_PATH = "C:\\JavaTest\\TestBeforeTest.json";

    @Test
    void shouldExportProductsToJsonFile() throws IOException {
        //given
        ArgumentCaptor<File> fileCaptor = ArgumentCaptor.forClass(File.class);
        List<Product> productList = Arrays.asList(
                new Product("Product 1 Test", 50.0, "Producer 1 Test"),
                new Product("Product 2 Test", 90.0, "Producer 2 Test"));

        when(productRepositoryMock.findAll()).thenReturn(productList);
        when(fileConfigMock.getDbFileExportPath()).thenReturn(EXPORT_PATH);

        //when
        exportToJsonService.exportProductsToJson();

        //then
        verify(objectMapperMock).writeValue(fileCaptor.capture(), eq(productList));
        assertThat(fileCaptor.getValue().getPath()).isEqualTo(EXPORT_PATH);
    }

}