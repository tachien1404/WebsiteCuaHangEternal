package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webbangiaydabong.dto.OrderDetailDTO;
import webbangiaydabong.entity.OrderDetail;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
    @Query("SELECT o FROM OrderDetail o where o.order.id= :id")
    List<OrderDetail> findOrderDetailByOrder(@Param("id") Long id);
    @Query(value = "SELECT * FROM orderdetail WHERE product_id=:productID AND s_c_details_id=:scId AND order_id=:orderId",nativeQuery = true)
    OrderDetail getByProductIdAndSC(long productID,Long scId,Long orderId);
    @Query("select new webbangiaydabong.dto.OrderDetailDTO(o) from OrderDetail o where o.order.id=:id")
    List<OrderDetailDTO>getByOrderId(Long id);
@Query("SELECT SUM(o.quantity)\n" +
        "FROM OrderDetail o\n" +
        "WHERE o.order.id=:orderid")
    Long sumquantity(Long orderid);
    @Query("SELECT SUM(o.price*o.quantity)\n" +
            "FROM OrderDetail o\n" +
            "WHERE o.order.id=:orderid")
    float sumgia(Long orderid);
    @Query("select count(o.id) from OrderDetail o where o.order.id=:id")
    Long sumsoluongmathang(long id);
}
