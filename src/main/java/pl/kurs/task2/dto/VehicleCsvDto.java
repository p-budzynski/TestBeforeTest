package pl.kurs.task2.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleCsvDto {
    @CsvBindByName(column = "type")
    private String type;

    @CsvBindByName(column = "brand")
    private String brand;

    @CsvBindByName(column = "model")
    private String model;

    @CsvBindByName(column = "numberofseats")
    private String numberOfSeats;

    @Override
    public String toString() {
        return  type + ", " + brand + ", " + model + ", " + numberOfSeats;
    }
}
