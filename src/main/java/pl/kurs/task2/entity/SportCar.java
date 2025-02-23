package pl.kurs.task2.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sport_car")
@SequenceGenerator(name = "sportcar_seq", sequenceName = "sportcar_seq", allocationSize = 1)
@NoArgsConstructor
public class SportCar extends Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sportcar_seq")
    private Long id;

    public SportCar(String brand, String model, int numberOfSeats) {
        super(brand, model, numberOfSeats);
    }
}
