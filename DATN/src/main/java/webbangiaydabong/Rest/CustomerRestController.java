package webbangiaydabong.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.dto.CustomerDto;
import webbangiaydabong.dto.OrderDTO;
import webbangiaydabong.service.CustomerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public/customer")
public class CustomerRestController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/save")
    public CustomerDto save(@RequestBody CustomerDto dto){
        CustomerDto customerDto=customerService.saveOrUpdate(null,dto);
        return customerDto;
    }
    @PutMapping ("/update/{id}")
    public CustomerDto update(@RequestBody CustomerDto dto,@PathVariable Long id){
        CustomerDto customerDto=customerService.saveOrUpdate(id,dto);
        return customerDto;
    }
    @GetMapping("/searchName/{name}")
    public CustomerDto searchName(@PathVariable String name){
        CustomerDto customerDto=customerService.searchName(name);
        return customerDto;
    }
    @GetMapping("/getByid/{id}")
    public CustomerDto getByid(@PathVariable Long id){
        CustomerDto customerDto=customerService.getbyid(id);
        return customerDto;
    }
}
