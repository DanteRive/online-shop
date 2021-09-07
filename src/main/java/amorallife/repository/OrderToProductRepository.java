package amorallife.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import amorallife.entity.OrderToProduct;

public interface OrderToProductRepository extends JpaRepository<OrderToProduct, Long> {
}
