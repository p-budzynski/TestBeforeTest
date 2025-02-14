package pl.kurs.task1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.task1.service.ProductExportToJsonService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ProductExportToJsonController {
    private final ProductExportToJsonService exportToJsonService;

    @PostMapping("/products/database-dump")
    public void exportProductsToJson() throws IOException {
        exportToJsonService.exportProductsToJson();
    }
}
