package pl.kurs.task2.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plane")
@SequenceGenerator(name = "plane_seq", sequenceName = "plane_seq", allocationSize = 1)
@NoArgsConstructor
public class Plane extends Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plane_seq")
    private Long id;

    public Plane(String brand, String model, int numberOfSeat) {
        super(brand, model, numberOfSeat);
    }
}
