package pl.kurs.task2.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bus")
@SequenceGenerator(name = "bus_seq", sequenceName = "bus_seq", allocationSize = 1)
@NoArgsConstructor
public class Bus extends Vehicle{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bus_seq")
    private Long id;

    public Bus(String brand, String model, int numberOfSeats) {
        super(brand, model, numberOfSeats);
    }
}
