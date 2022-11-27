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

	@Query("select o from Order o where o.account.username=?1")
	List<Order> findALlOrderByAccount(String userName);

	@Query("select new webbangiaydabong.dto.OrderDTO(o,true) from Order o where o.id=:id")
	OrderDTO getOrderbyid(Long id);


	@Query("select o from Order o where o.account.username=?1 and o.status=?2")
	List<Order> findAllByStatusAndUser(String userName,Integer status);

	@Query("select o from Order o where o.status=?1")
	List<Order> findAllByStatus(Integer integer);

	List<Order> findAllByStatus(String userName,Integer status);

}
