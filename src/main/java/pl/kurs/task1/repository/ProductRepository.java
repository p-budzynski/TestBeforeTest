package pl.kurs.task1.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kurs.task1.entity.Product;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class ProductRepository {
    private final EntityManager entityManager;

    public void save(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        } else {
            entityManager.merge(product);
        }
    }

    public Product findById(Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product == null) {
            throw new EntityNotFoundException("Product ID " + id + " not found.");
        }
        return product;
    }

    public List<Product> findAll() {
        List<Product> products = entityManager.createQuery("SELECT p FROM Product p").getResultList();
        if (products.isEmpty()) {
            throw new EntityNotFoundException("No products in the database.");
        }
        return products;
    }

    public void deleteById(Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product == null) {
            throw new EntityNotFoundException("Product ID " + id + " not found.");
        }
        entityManager.remove(product);
    }
}
