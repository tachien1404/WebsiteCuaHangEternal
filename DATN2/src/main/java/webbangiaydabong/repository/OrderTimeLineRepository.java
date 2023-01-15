package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webbangiaydabong.dto.OrderTimeLineDTO;
import webbangiaydabong.entity.OrderTimeLine;

import java.util.List;

@Repository
public interface OrderTimeLineRepository extends JpaRepository<OrderTimeLine,Long> {
    @Query("select new webbangiaydabong.dto.OrderTimeLineDTO(o) from OrderTimeLine o where o.order.id =:oId and o.account.id =:aId " +
            "group by o.id" +
            " order by o.id desc ")
  List<OrderTimeLineDTO> getByOrderIdAndAccountId(Long oId,Long aId);
}
