package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webbangiaydabong.entity.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
    @Query("SELECT o FROM OrderDetail o where o.order.id= ?1")
    List<OrderDetail> findOrderDetailByOrder(@Param("id") long id);
}
