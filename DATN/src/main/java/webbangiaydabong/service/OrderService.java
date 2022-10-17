package webbangiaydabong.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.dto.functiondto.DatHangDto;
import webbangiaydabong.entity.Order;
@Service

public interface OrderService {

	Order create(JsonNode orderData);
	Object finById(Long id);
	List<Order> findByUserId(Long id);
	OrderDTO save(DatHangDto dto);
	List<OrderDTO>order(OrderDTO dto);
	List<OrderDTO>getAllByStatus(Integer status);
	ResponseEntity add(List<DatHangDto> dto);
}
