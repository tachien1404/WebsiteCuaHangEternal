package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webbangiaydabong.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
