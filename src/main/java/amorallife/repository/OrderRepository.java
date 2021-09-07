package amorallife.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import amorallife.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
