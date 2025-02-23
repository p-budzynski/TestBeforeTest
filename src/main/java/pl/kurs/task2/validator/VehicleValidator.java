package pl.kurs.task2.validator;

import org.springframework.stereotype.Component;
import pl.kurs.task2.dto.VehicleCsvDto;

@Component
public class VehicleValidator {
    public boolean isValid(VehicleCsvDto dto) {
        if (dto == null || dto.getType() == null || dto.getBrand() == null || dto.getModel() == null) {
            return false;
        }
        try {
            return Integer.parseInt(dto.getNumberOfSeats()) > 0;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
