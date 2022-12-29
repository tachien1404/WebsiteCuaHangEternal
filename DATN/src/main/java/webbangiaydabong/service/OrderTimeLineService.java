package webbangiaydabong.service;

import webbangiaydabong.dto.OrderTimeLineDTO;

import java.util.List;

public interface OrderTimeLineService {
    OrderTimeLineDTO save(OrderTimeLineDTO dto);
    List<OrderTimeLineDTO>getBy(Long order_id,Long account_id);
}
