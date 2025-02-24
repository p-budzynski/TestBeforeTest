package pl.kurs.task2.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kurs.task2.entity.Vehicle;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class VehicleRepository {
    private final EntityManager entityManager;

    public void saveAll(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            entityManager.persist(vehicle);
        }
    }
}
