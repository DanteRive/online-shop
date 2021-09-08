package amorallife.repository;

import amorallife.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import amorallife.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUserId(Long id);
}
