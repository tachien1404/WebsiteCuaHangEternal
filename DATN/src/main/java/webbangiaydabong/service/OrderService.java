package webbangiaydabong.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import webbangiaydabong.entity.Order;

public interface OrderService {

	Order create(JsonNode orderData);
	Object finById(Long id);
	List<Order> findByUserId(Long id);
}
