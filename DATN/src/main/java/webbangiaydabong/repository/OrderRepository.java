package webbangiaydabong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.entity.Order;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long>{

	@Query("SELECT o FROM Order o WHERE o.account.id=?1")
	List<Order> findByUserId(Long id);
	@Query("select new webbangiaydabong.dto.OrderDTO(o,true) from Order o where o.status =:status")
	List<OrderDTO> getAllByStatus(Integer status);

	@Query("select o from Order o where o.account.userName=?1")
	List<Order> findALlOrderByAccount(String userName);
}
