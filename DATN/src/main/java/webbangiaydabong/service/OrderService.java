package webbangiaydabong.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.dto.functiondto.DatHangDto;
import webbangiaydabong.entity.Order;

public interface OrderService {

	Order create(JsonNode orderData);
	Object finById(Long id);
	List<Order> findByUserId(Long id);
	OrderDTO save(DatHangDto dto);
	List<OrderDTO>order(OrderDTO dto);
}
