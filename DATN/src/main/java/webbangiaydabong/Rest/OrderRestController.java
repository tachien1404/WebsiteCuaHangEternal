package webbangiaydabong.Rest;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.dto.OrderDetailDTO;
import webbangiaydabong.dto.functiondto.DatHangDto;
import webbangiaydabong.dto.functiondto.SearchDto;
import webbangiaydabong.entity.Order;
import webbangiaydabong.service.OrderService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/order")
	@CrossOrigin("*")

public class OrderRestController {
	@Autowired
	OrderService orderService;

	@PostMapping("/dathang")
	public ResponseEntity<Order> dathang(@RequestBody List<DatHangDto> dto) {

		return orderService.add(dto);
	}
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
}
