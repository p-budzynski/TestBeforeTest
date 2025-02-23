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
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleParser {
    private final VehicleValidator vehicleValidator;

    public List<VehicleCsvDto> parseCsv(MultipartFile file) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<VehicleCsvDto> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(VehicleCsvDto.class);

            CsvToBean<VehicleCsvDto> csvToBean = new CsvToBeanBuilder<VehicleCsvDto>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> VehicleCsvDto.builder()
                            .type(csvLine.getType())
                            .brand(csvLine.getBrand())
                            .model(csvLine.getModel())
                            .numberOfSeats(csvLine.getNumberOfSeats())
                            .build())
                    .collect(Collectors.toList());
        }
    }
}