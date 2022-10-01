package webbangiaydabong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	@Query("SELECT o FROM Order o WHERE o.account.id=?1")
	List<Order> findByUserId(Long id);
	@Query(name="SELECT `order`.id,`order`.create_date,`order`.price,`order`.note,account.fullname\r\n"
			+ "\r\n"
			+ "FROM `order` JOIN account ON `order`.account_id=account.id\r\n"
			+ "WHERE `order`.`status`=?1\r\n"
			+ "GROUP BY `order`.id\r\n"
			+ "",nativeQuery = true)
	List<OrderDTO>listdondathang(Integer status);
}
