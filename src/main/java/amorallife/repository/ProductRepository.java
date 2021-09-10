package amorallife.repository;

import amorallife.entity.ProductType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import amorallife.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    List<Product> findAllByOrderByPrice(Pageable pageable);

    List<Product> findAllByIdIn(List<Long> ids);

    List<Product> findAllByProductType(ProductType productType, Pageable pageable);

    List<Product> findAllByNameLike(String name , Pageable pageable);

}
