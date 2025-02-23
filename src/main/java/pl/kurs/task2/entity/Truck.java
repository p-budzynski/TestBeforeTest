package pl.kurs.task2.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "truck")
@SequenceGenerator(name = "truck_seq", sequenceName = "truck_seq", allocationSize = 1)
@NoArgsConstructor
public class Truck extends Vehicle{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "truck_seq")
    private Long id;

    public Truck(String brand, String model, int numberOfSeats) {
        super(brand, model, numberOfSeats);
    }
}
