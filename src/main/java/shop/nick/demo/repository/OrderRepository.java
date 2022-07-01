package shop.nick.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.nick.demo.entity.Order;
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
