package webbangiaydabong.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.dto.OrderDetailDTO;
import webbangiaydabong.dto.functiondto.DatHangDto;
import webbangiaydabong.entity.Account;
import webbangiaydabong.entity.Order;
import webbangiaydabong.entity.OrderDetail;
import webbangiaydabong.entity.Product;
import webbangiaydabong.repository.AccountRepository;
import webbangiaydabong.repository.OrderDetailRepository;
import webbangiaydabong.repository.OrderRepository;
import webbangiaydabong.repository.ProductRepository;
import webbangiaydabong.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepo;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	OrderDetailRepository orderDetailRepo;
	@Autowired
	AccountRepository accountRepository;

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.convertValue(orderData, Order.class);
		orderRepo.save(order);
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
		};
		List<OrderDetail> details = mapper.convertValue(orderData.get("danhSachOrder"), type).stream()
				.peek(d -> d.setOrder(order)).collect(Collectors.toList());
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

	@Override
	public OrderDTO save(DatHangDto dto) {
//		Order order = new Order();
//		if (dto.getTongtien() > 0) {
//			order.setPrice(dto.getTongtien());
//		}
//		if (dto.getAccount_id() != null) {
//			Account acc = accountRepository.getOne(dto.getAccount_id());
//			order.setAccount(acc);
//		}
//		if (dto.getNote() != null) {
//			order.setNote(dto.getNote());
//		}
//		if (dto.getStatus()<0) {
//			order.setStatus(0);
//		}
//		if (dto.getChitiethoadon() != null) {
//			Iterator<OrderDetailDTO> iters = dto.getChitiethoadon().iterator();
//			HashSet<OrderDetail> orderdetais = new HashSet<OrderDetail>();
//			while (iters.hasNext()) {
//				OrderDetailDTO detailDTO = iters.next();
//				OrderDetail orderDetail = new OrderDetail();
//				if (detailDTO.getCreateDate() != null) {
//					orderDetail.setCreateDate(detailDTO.getCreateDate());
//				}
//				if (detailDTO.getPrice() > 0) {
//					orderDetail.setPrice(detailDTO.getPrice());
//				}
//				if (detailDTO.getQuantity() > 0) {
//					orderDetail.setQuantity(detailDTO.getQuantity());
//				}
//				if (detailDTO.getProduct_id() != null) {
//					Product product = productRepository.getOne(detailDTO.getProduct_id());
//					orderDetail.setProduct(product);
//				}
//				if (orderDetail != null) {
//					orderDetail.setOrder(order);
//				}
//				orderdetais.add(orderDetail);
//				orderDetailRepo.save(orderDetail);
//			}
//			if (order.getDanhSachOrder() != null) {
//				order.getDanhSachOrder().clear();
//				order.getDanhSachOrder().addAll(orderdetais);
//			} else {
//				order.setDanhSachOrder(orderdetais);
//			}
//		}
//		order = orderRepo.save(order);
		return null;
	}

	@Override
	public List<OrderDTO> order(OrderDTO dto) {
		
		return null;
	}

	@Override
	public List<OrderDTO> getAllByStatus(Integer status) {
	return	orderRepo.getAllByStatus(status);
		
	}

	@Override
	public ResponseEntity add(List<DatHangDto> dto) {
		System.out.println(dto.toString());

		Order order = new Order();
		if (!dto.isEmpty()){
			float total = 0;
			for (DatHangDto tong : dto){
				total += tong.getTotal();
			}
			order.setPrice((double) total);
			order.setStatus(0);

			orderRepo.save(order);

			for (DatHangDto c : dto){
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setPrice(Float.parseFloat(String.valueOf(c.getOutputprice())));
				orderDetail.setQuantity(c.getQuantity());
				orderDetail.setOrder(order);
				orderDetail.setProduct(productRepository.getById(c.getId()));
				orderDetailRepo.save(orderDetail);
			}
		}
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	@Override
	public void updatetrangthai(Long id, OrderDTO dto) {
		if(id!=null){
			Order order=orderRepo.getById(id);
			if(dto.getStatus()>=0){
				order.setStatus(dto.getStatus());
				orderRepo.save(order);
			}else {
				return;
			}


		}else{
			return;
		}


	}
}
