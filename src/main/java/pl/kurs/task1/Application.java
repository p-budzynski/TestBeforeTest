package pl.kurs.task1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.kurs.task1.entity.Product;
import pl.kurs.task1.repository.ProductRepository;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        ProductRepository productRepository = ctx.getBean(ProductRepository.class);

        productRepository.save(new Product("Phone", 950.0,"Samsung"));
        productRepository.save(new Product("Laptop", 1500.0,"Lenovo"));
        productRepository.save(new Product("TV", 1200.0,"Sony"));
        productRepository.save(new Product("Car", 10000.0,"Toyota"));


    }
}
