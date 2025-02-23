package pl.kurs.task2.parrser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pl.kurs.task2.dto.VehicleCsvDto;
import pl.kurs.task2.validator.VehicleValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleParser {
    private final VehicleValidator vehicleValidator;

    public List<VehicleCsvDto> parseCsv(MultipartFile file) throws IOException {
        List<VehicleCsvDto> validVehicles = new ArrayList<>();

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<VehicleCsvDto> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(VehicleCsvDto.class);

            CsvToBean<VehicleCsvDto> csvToBean = new CsvToBeanBuilder<VehicleCsvDto>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withThrowExceptions(false)
                    .build();

            for (VehicleCsvDto csvLine : csvToBean) {
                if (vehicleValidator.isValid(csvLine)) {
                    validVehicles.add(new VehicleCsvDto(
                            csvLine.getType(),
                            csvLine.getBrand(),
                            csvLine.getModel(),
                            csvLine.getNumberOfSeats()
                    ));
                } else {
                    System.err.println("Invalid csv line: " + csvLine);
                }
            }
        }
        return validVehicles;
    }
}