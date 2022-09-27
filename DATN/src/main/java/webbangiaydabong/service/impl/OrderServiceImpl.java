package webbangiaydabong.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import webbangiaydabong.entity.Order;
import webbangiaydabong.entity.OrderDetail;
import webbangiaydabong.repository.OrderDetailRepository;
import webbangiaydabong.repository.OrderRepository;
import webbangiaydabong.service.OrderService;

public class OrderServiceImpl implements OrderService {

	@Autowired 
	OrderRepository orderRepo;
	
	@Autowired
	OrderDetailRepository orderDetailRepo;

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.convertValue(orderData, Order.class);
		orderRepo.save(order);
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
		};
		List<OrderDetail> details = mapper.convertValue(orderData.get("danhSachOrder"), type)
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		orderDetailRepo.saveAll(details);
		return order;
	}

	@Override
	public Object finById(Long id) {
		return orderRepo.findById(id).get();
	}

	@Override
	public List<Order> findByUserId(Long id) {
		return orderRepo.findByUserId(id);
	}
	
	
}
