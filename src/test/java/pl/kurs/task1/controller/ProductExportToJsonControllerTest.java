package pl.kurs.task1.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.kurs.task1.service.ProductExportToJsonService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductExportToJsonControllerTest {

    @Mock
    private ProductExportToJsonService exportToJsonServiceMock;

    @InjectMocks
    private ProductExportToJsonController productExportToJsonController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productExportToJsonController).build();
    }

    @Test
    void shouldExportProductsToJson() throws Exception {
        //given
        doNothing().when(exportToJsonServiceMock).exportProductsToJson();

        //when then
        mockMvc.perform(post("/products/database-dump")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(exportToJsonServiceMock).exportProductsToJson();
    }

}
