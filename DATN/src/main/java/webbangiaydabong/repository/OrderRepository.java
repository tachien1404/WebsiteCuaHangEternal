package webbangiaydabong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import webbangiaydabong.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	@Query("SELECT o FROM Order o WHERE o.account.id=?1")
	List<Order> findByUserId(Long id);
}
