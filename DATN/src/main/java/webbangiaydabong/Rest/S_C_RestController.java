package webbangiaydabong.Rest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.dto.S_C_DetailDTO;
import webbangiaydabong.entity.S_C_Details;
import webbangiaydabong.service.ColorService;
import webbangiaydabong.service.ProductService;
import webbangiaydabong.service.S_C_DetailService;
import webbangiaydabong.service.SizeService;

import java.util.List;

@RestController
@RequestMapping("/rest/s_c_details")
public class S_C_RestController {
    @Autowired
    S_C_DetailService service;

    @Autowired
    ProductService productService;

    @Autowired
    SizeService sizeService;

    @Autowired
    ColorService colorService;

    @GetMapping
    public List<S_C_Details> getAll(){
        return service.findAll();
    }

    @GetMapping("/s_c")
    public S_C_Details getSC(@RequestParam("product_id") Long id, @RequestParam("size_id") Long sid,
                              @RequestParam("color_id")  Long cid) {
        return service.findBySizeColor(id,sid,cid);
    }

    @GetMapping("/s")
    public List<S_C_Details> getS(@RequestParam("product_id") Long id, @RequestParam("size_id") Long sid) {
        return service.findBySize(id,sid);
    }

    @GetMapping("/c")
    public List<S_C_Details> getC(@RequestParam("product_id") Long id,@RequestParam("color_id")  Long cid) {
        return service.findByColor(id, cid);
    }

    @PostMapping
    public S_C_Details create(@RequestBody S_C_DetailDTO dto) {
       S_C_Details entity= new S_C_Details();
        BeanUtils.copyProperties(dto,entity);
        entity.setProduct(productService.findById(dto.getProduct_id()));
        entity.setSize(sizeService.findById(dto.getSize_id()));
        entity.setMau(colorService.findById(dto.getColor_id()));
        return  service.create(entity);
    }



}
