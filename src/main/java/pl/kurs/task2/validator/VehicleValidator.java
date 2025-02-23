package pl.kurs.task2.validator;

import org.springframework.stereotype.Component;
import pl.kurs.task2.dto.VehicleCsvDto;

@Component
public class VehicleValidator {
    public boolean isValid(VehicleCsvDto dto) {
        return dto.getType() != null && dto.getType().isEmpty()
                && dto.getBrand() != null && dto.getBrand().isEmpty()
                && dto.getModel() != null && dto.getModel().isEmpty()
                && dto.getNumberOfSeats() > 0;
    }
}
