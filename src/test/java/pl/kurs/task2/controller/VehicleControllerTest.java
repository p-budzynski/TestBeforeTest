package pl.kurs.task2.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pl.kurs.task2.service.VehicleService;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleControllerTest {

    @Mock
    private VehicleService vehicleServiceMock;

    @InjectMocks
    private VehicleController vehicleController;

    private MockMultipartFile mockFile;

    @BeforeEach
    void setup() {
        mockFile = new MockMultipartFile(
                "file", "vehicles.csv", MediaType.TEXT_PLAIN_VALUE, "content".getBytes());
    }

    @Test
    void shouldReturnCorrectNumberOfUploadedVehicles() throws IOException {
        // Given
        int expectedCount = 10;
        when(vehicleServiceMock.uploadVehicles(any(MultipartFile.class))).thenReturn(expectedCount);

        //when
        ResponseEntity<Integer> response = vehicleController.uploadVehicles(mockFile);

        //then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedCount);
    }

}
