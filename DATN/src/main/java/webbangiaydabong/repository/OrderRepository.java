package webbangiaydabong.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.dto.Report;
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


	@Query("select o from Order o where o.account.username= :userName and o.status= :status")
	List<Order> findAllByStatusAndUserName(@Param("userName") String userName,
										   @Param("status") Integer status);

	@Query(nativeQuery = true,
	value = "select count(status) from order where status = 0 and create_date between ?1 and ?2\n"+
	        "union all\n" +
			"select count(status) from order where status = 1 and create_date between ?1 and ?2\n"+
			"union all\n" +
			"select count(status) from order where status = 2 and create_date between ?1 and ?2\n"+
			"union all\n" +
			"select count(status) from order where status = 3 and create_date between ?1 and ?2\n"+
			"union all\n" +
			"select count(status) from order where status = 4 and create_date between ?1 and ?2\n"+
			"union all\n" +
			"select count(status) from order where status = 5 and create_date between ?1 and ?2\n"+
			"union all\n" +
			"select count(status) from order where status = 7 and create_date between ?1 and ?2\n")
	List<Integer> statistical(Date createDate, Date endDate);

}
