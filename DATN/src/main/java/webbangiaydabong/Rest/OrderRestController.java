package webbangiaydabong.Rest;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.dto.functiondto.DatHangDto;
import webbangiaydabong.entity.Order;
import webbangiaydabong.service.OrderService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/order")
public class OrderRestController {
	@Autowired
	OrderService orderService;

	@PostMapping("/dathang")
	public ResponseEntity<Order> dathang(@RequestBody List<DatHangDto> dto) {

		return orderService.add(dto);
	}
	@GetMapping("/getAllbystatus")
	public List<OrderDTO>getAllbyStatus(){
		return orderService.getAllByStatus();
	}
}
