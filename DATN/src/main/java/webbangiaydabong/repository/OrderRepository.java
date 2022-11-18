package webbangiaydabong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	@Query("SELECT o FROM Order o WHERE o.account.id=?1")
	List<Order> findByUserId(Long id);
	@Query("select new webbangiaydabong.dto.OrderDTO(o,true) from Order o where status =:status")
	List<OrderDTO> getAllByStatus(Integer status);
	@Modifying
	@Transactional
@Query(value = "UPDATE  `order` SET `order`.status=:status  where 1=1",nativeQuery = true)
  void update(Integer status);
}
