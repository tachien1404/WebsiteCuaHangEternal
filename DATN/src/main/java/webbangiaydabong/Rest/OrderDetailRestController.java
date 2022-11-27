package webbangiaydabong.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.entity.OrderDetail;
import webbangiaydabong.service.OrderDetaiService;
import webbangiaydabong.service.OrderService;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("api/public/order-detail")
public class OrderDetailRestController {
    @Autowired
    OrderDetaiService orderDetaiService;
    @Autowired
    OrderService orderService;

    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable("id")Long id){
        List<OrderDetail> detailList = orderDetaiService.findByIDOrder(id);
        return new ResponseEntity<>(detailList, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")Long id){
        orderDetaiService.xoa(id);
        return;
    }
}
