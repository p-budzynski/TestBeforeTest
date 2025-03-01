package pl.kurs.task2.parrser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pl.kurs.task2.dto.VehicleCsvDto;
import pl.kurs.task2.validator.VehicleValidator;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleParserTest {

    @Mock
    private VehicleValidator vehicleValidatorMock;

    @InjectMocks
    private VehicleParser vehicleParser;

    private MultipartFile validCsvFile;
    private MultipartFile invalidCsvFile;

    @BeforeEach
    void setup() {
        String validCsvContent = "type,brand,model,numberOfSeats\n"
                + "sportcar,Toyota,Supra,2\n"
                + "truck,Volvo,FH16,2\n";

        validCsvFile = new MockMultipartFile(
                "file", "vehicles.csv", "text/csv", validCsvContent.getBytes());

        String invalidCsvContent = "type,brand,model,numberOfSeats\n"
                + "sportcar,Ford,GT,abc\n"
                + "truck,Volvo,FH16,2\n";

        invalidCsvFile = new MockMultipartFile(
                "file", "invalid_vehicles.csv", "text/csv", invalidCsvContent.getBytes());
    }

    @Test
    void shouldParseValidCsvFile() throws IOException {
        //given
        when(vehicleValidatorMock.isValid(any(VehicleCsvDto.class))).thenReturn(true);

        //when
        List<VehicleCsvDto> result = vehicleParser.parseCsv(validCsvFile);

        //then
        assertThat(result).hasSize(2);
        assertThat(result.get(0)).extracting("type", "brand", "model", "numberOfSeats")
                .containsExactly("sportcar", "Toyota", "Supra", "2");
        assertThat(result.get(1)).extracting("type", "brand", "model", "numberOfSeats")
                .containsExactly("truck", "Volvo", "FH16", "2");

        verify(vehicleValidatorMock, times(2)).isValid(any(VehicleCsvDto.class));
    }

    @Test
    void shouldFilterOutInvalidCsvLines() throws IOException {
        //given
        when(vehicleValidatorMock.isValid(any(VehicleCsvDto.class))).thenReturn(false, true);

        //when
        List<VehicleCsvDto> result = vehicleParser.parseCsv(invalidCsvFile);

        //then
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).extracting("type", "brand", "model", "numberOfSeats")
                .containsExactly("truck","Volvo","FH16","2");

        verify(vehicleValidatorMock, times(2)).isValid(any(VehicleCsvDto.class));
    }
}
