package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.OrderDetail;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
    @Query("SELECT o FROM OrderDetail o where o.order.id= :id")
    List<OrderDetail> findOrderDetailByOrder(@Param("id") long id);
}
