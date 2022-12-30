package webbangiaydabong.service;

import webbangiaydabong.dto.OrderTimeLineDTO;

import java.util.List;

public interface OrderTimeLineService {
    OrderTimeLineDTO save(OrderTimeLineDTO dto);
    List<OrderTimeLineDTO>getBy(Long order_id,String accountname);
}
