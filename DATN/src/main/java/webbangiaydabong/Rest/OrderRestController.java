package webbangiaydabong.Rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.xspec.L;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.dto.OrderDetailDTO;
import webbangiaydabong.dto.Report;
import webbangiaydabong.dto.functiondto.DatHangDto;
import webbangiaydabong.dto.functiondto.SearchDto;
import webbangiaydabong.entity.*;
import webbangiaydabong.service.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public/order")

public class OrderRestController {

	@Autowired
	AccountService accountService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderDetaiService orderDetaiService;

	@Autowired
	CustommerInfoServie custommerInfoServie;

	@Autowired
	S_C_DetailService s_c_detailService;

	@Autowired
	CartService cartService;


	@GetMapping("/getAllbystatus/{status}")
	public List<OrderDTO>getAllbyStatus(@PathVariable Integer status){
		return orderService.getAllByStatus(status);
	}
	@PutMapping("/trangthai/{id}")
	public void update(@PathVariable ("id") Long id,@RequestBody OrderDTO dto){
		orderService.updatetrangthai(id,dto);
	}
	@RequestMapping(value = "/search-by-page", method = RequestMethod.POST)
	public ResponseEntity<Page<OrderDTO>> searchByPage(@RequestBody SearchDto dto) {
		Page<OrderDTO> result = orderService.searchByPage(dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	@GetMapping("/getOrderId/{id}")
	public List<OrderDetailDTO> get(@PathVariable Long id){
		return orderService.getByOrderId(id);
	}

	@GetMapping("/getOrderById/{id}")
	public OrderDTO getOrderId(@PathVariable Long id){
		return orderService.getOrderbyid(id);
	}
	@PostMapping("/saveAll")
	public ResponseEntity<ResponseObject> saveAll(@RequestParam("userName") String userName,
												 @RequestParam("idCustommer") Long idCustommer){

		List<Cart> listcart = cartService.findAllByUserName(userName);
		Account accountdb = accountService.findByUserName(userName);
		try {
			Order order = new Order();
			Date date = new Date();
			double price =0;
			for (Cart c: listcart) {
				price+=(c.getProduct().getOutputprice() * c.getQuantity());
			}
			price +=30000;
			order.setPrice(price);
			order.setStatus(0);

			order.setAccount(accountdb);


			CustommerInfo custommerInfodb = custommerInfoServie.findById(idCustommer);
			order.setDiaChi(custommerInfodb);
			Order orderdb =	orderService.saveOder(order);

//------------OrdderDetail----------------

			for (Cart c: listcart) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setPrice(c.getProduct().getOutputprice());
				orderDetail.setQuantity(c.getQuantity());
				orderDetail.setCreateDate(date);
				orderDetail.setProduct(c.getProduct());
				orderDetail.setOrder(orderdb);
				S_C_Details s_c_details =s_c_detailService.findBySizeColor(c.getProduct().getId(),c.getSize().getId(),c.getMau().getId());
				orderDetail.setSaimau(s_c_details);
				orderDetaiService.create(orderDetail);

//				Cập nhật số lượng
				int sl = s_c_details.getQuantity() -c.getQuantity();
				s_c_details.setQuantity(sl);
				s_c_detailService.update(s_c_details);
			}

			cartService.deleteAllByUserName(userName);
			return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Đặt hàng thành công", ""));
		}catch (Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseObject(HttpStatus.BAD_REQUEST,
							"Đặt hàng thất bại", ""));
		}
	}

	@GetMapping("/showHistory")
	public ResponseEntity<ResponseObject> showHistory(@RequestParam("userName") String userName){
		List<Order> listOrder = orderService.showHistoryByAccount(userName);
		return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Thành công", listOrder));
	}

	@GetMapping("/orderDeails/{id}")
	public ResponseEntity<ResponseObject> showHistory(@PathVariable("id") Long id){
		List<OrderDetail> orderDetailList = orderDetaiService.findByOder(id);
		return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Thành công", orderDetailList));
	}

	@PostMapping("/huyDon/{id}")
	public ResponseEntity<ResponseObject> huyDon(@PathVariable("id") Long id,@RequestBody String note){
		Order order = orderService.getById(id);
		if(order.getStatus()==0){
			List<OrderDetail> orderDetailList = orderDetaiService.findByOder(id);
			for (OrderDetail o: orderDetailList ) {
				S_C_Details sc = s_c_detailService.findBySizeColor(o.getSaimau().getProduct().getId(),o.getSaimau().getSize().getId(),
						o.getSaimau().getMau().getId());
				sc.setQuantity(sc.getQuantity()+o.getQuantity());
				s_c_detailService.update(sc);
			}
			order.setNote(note);
			order.setStatus(4);
			orderService.saveOder(order);
		}
		return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Hủy đơn thành công",""));
	}

	@PostMapping("/buyNow")
	public ResponseEntity<ResponseObject> buyNow(@RequestParam("userName") String userName,
												  @RequestParam("idCustommer") Long idCustommer,
												  @RequestBody Cart cart){
		Account accountdb = accountService.findByUserName(userName);
		try {
			Order order = new Order();
			Date date = new Date();
			double price =0;
				price=(cart.getProduct().getOutputprice() * cart.getQuantity())+30000;
			order.setPrice(price);
			order.setStatus(0);


			order.setAccount(accountdb);

			CustommerInfo c = custommerInfoServie.findById(idCustommer);
			order.setDiaChi(c);
			Order orderdb =	orderService.saveOder(order);

//------------OrdderDetail----------------

				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setPrice(cart.getProduct().getOutputprice());
				orderDetail.setQuantity(cart.getQuantity());
				orderDetail.setCreateDate(date);
				orderDetail.setProduct(cart.getProduct());
				orderDetail.setOrder(orderdb);
				S_C_Details s_c_details =s_c_detailService.findBySizeColor(cart.getProduct().getId(),cart.getSize().getId(),cart.getMau().getId());
				orderDetail.setSaimau(s_c_details);
				orderDetaiService.create(orderDetail);

//				Cập nhật số lượng
				int sl = s_c_details.getQuantity() -cart.getQuantity();
				s_c_details.setQuantity(sl);
				s_c_detailService.update(s_c_details);


			cartService.deleteAllByUserName(userName);
			return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Đặt hàng thành công", ""));
		}catch (Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseObject(HttpStatus.BAD_REQUEST,
							"Đặt hàng thất bại", ""));
		}

	}

	@GetMapping("/getByStatus")
	public List<Order> showHistory(@RequestParam("userName") String userName
			,@RequestParam("status") Integer status){
		return orderService.getByStatus(userName,status);
	}

	@GetMapping("/findByDay")
		public List<Report> findByDay(@RequestParam("create") Date create,
									  @RequestParam("end") Date end){
		 return orderService.findByDate(create,end);
		}

	@PostMapping("/save")
	public OrderDTO save(@RequestBody OrderDTO dto){
		return orderService.save(null,dto);
	}
	@PostMapping("/update/{id}")
	public OrderDTO update( @RequestBody OrderDTO dto,@PathVariable Long id){
		return orderService.save(id,dto);
	}
	@DeleteMapping("/delete/{id}")
	public  void delete(@PathVariable Long id){
		orderService.delete(id);
	}
}
