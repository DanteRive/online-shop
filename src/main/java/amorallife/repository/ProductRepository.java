package amorallife.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import amorallife.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    List<Product> findAllByOrderByPrice();

    List<Product> findAllByIdIn(List<Long> ids);
}
