package webbangiaydabong.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.dto.OrderTimeLineDTO;
import webbangiaydabong.service.OrderTimeLineService;

import java.util.List;

@RestController
@CrossOrigin("*" )
@RequestMapping("/api/public/timeline")
public class OrderTimeLineRestController {
    @Autowired
    OrderTimeLineService orderTimeLineService;
    @PostMapping("/save")
    public OrderTimeLineDTO save(@RequestBody OrderTimeLineDTO dto){
        return orderTimeLineService.save(dto);
    }
    @PostMapping ("/getby")
   public List<OrderTimeLineDTO>get(@RequestBody OrderTimeLineDTO dto){
        Long orderid=dto.getOrder_id();
        String accountname=dto.getAccount_name();
        return orderTimeLineService.getBy(orderid,accountname);
    }
}
