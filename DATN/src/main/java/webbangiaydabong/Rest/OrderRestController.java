package webbangiaydabong.Rest;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.dto.functiondto.DatHangDto;
import webbangiaydabong.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {
	@Autowired
	OrderService orderService;

	@PostMapping("/dathang")
	public ResponseEntity<?> dathang(@RequestBody DatHangDto dto) {
		orderService.save(dto);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	@GetMapping("/getAllbystatus")
	public List<OrderDTO>getAllbyStatus(){
		return orderService.getAllByStatus();
	}
}
