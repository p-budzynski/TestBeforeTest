package pl.kurs.task1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import pl.kurs.task1.config.FileConfig;
import pl.kurs.task1.entity.Product;
import pl.kurs.task1.repository.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class ProductExportToJsonService {
    private final ProductRepository productRepository;
    private final FileConfig fileConfig;
    private final ObjectMapper objectMapper;

    public void exportProductsToJson() throws IOException {
        List<Product> productList = productRepository.findAll();
        objectMapper.writeValue(new File(fileConfig.getDbFileExportPath()), productList);
    }
}
