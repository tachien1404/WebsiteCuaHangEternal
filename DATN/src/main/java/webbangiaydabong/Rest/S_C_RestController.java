package webbangiaydabong.Rest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.dto.*;
import webbangiaydabong.dto.functiondto.SortByValue;
import webbangiaydabong.entity.*;
import webbangiaydabong.service.ColorService;
import webbangiaydabong.service.ProductService;
import webbangiaydabong.service.S_C_DetailService;
import webbangiaydabong.service.SizeService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
        @RequestMapping("/api/public/s_c_details")
public class S_C_RestController {
    @Autowired
    S_C_DetailService service;

    @Autowired
    ProductService productService;

    @Autowired
    SizeService sizeService;

    @Autowired
    ColorService colorService;

    @GetMapping("")
    public List<S_C_Details> getAll() {
        return service.findAll();
    }

    @GetMapping("/getAllColor")
    public List<Color> getAllColor() {
        return colorService.findAll();
    }

    @GetMapping("/getAllSize")
    public List<Size> getAllSize() {
        return sizeService.findAll();
    }

    @GetMapping("/getAllProduct")
    public List<Product> getAllProduct() {
        return productService.findByStatus();
    }

    @GetMapping("/s_c")
    public S_C_Details getSC(@RequestParam("product_id") Long id, @RequestParam("size_id") Long sid,
                             @RequestParam("color_id") Long cid) {
        return service.findBySizeColor(id, sid, cid);
    }

    @PutMapping("/configProduct")
    public ResponseEntity<ResponseObject> getConfigProduct(@RequestParam int page,
                                              @RequestParam int size,
                                              @RequestParam("product_id") Long id) {
        try {
            page = page < 0 ? 0 : page;
//            size = size < 0 ? 5 : page;
            Pageable pageable;
            pageable = PageRequest.of(page, size, Sort.by("id"));
            Page<S_C_Details> s_c_detailsPage;
            s_c_detailsPage = service.findConfigProduct(pageable,id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(HttpStatus.OK, "Tìm thấy thành công", s_c_detailsPage)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Không tìm thấy",
                            ""));
        }
    }

    @PostMapping()
    public ResponseEntity<ResponseObject> create(@RequestBody S_C_Details scDetails) {
        try {
            if (scDetails.getQuantity() > 0) {
                scDetails.setStatus(1);
            } else {
                scDetails.setStatus(0);
            }
            service.create(scDetails);
            return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Thêm mới thành công", ""));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Thêm mới thất bại",
                            ""));
        }

    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    @PutMapping("/sortByKey")
    public ResponseEntity<ResponseObject> sortByKey(@RequestParam int page,
                                                    @RequestParam int size,
                                                    @RequestBody S_C_DetailSearch search ) {
        try {
            page = page < 0 ? 0 : page;
            Pageable pageable;
            List<Sort.Order> orders = new ArrayList<>();
            List<SortByValue> sortByValueList = search.getSortByValues();
            System.out.println(sortByValueList);
            if (sortByValueList.isEmpty()) {
                orders.add(new Sort.Order(Sort.Direction.ASC, "id") {
                });
            } else {
                sortByValueList.forEach(value -> {
                    orders.add(new Sort.Order(getSortDirection(value.getType()), value.getName()));
                });
            }

            pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<S_C_Details> s_c_detailsPage;
            s_c_detailsPage = service.findByKey(pageable, search.getId(), search.getProduct(),
                    search.getSize(), search.getMau(), search.getQuantity(), search.getStatus());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(HttpStatus.OK, "Tìm thấy thành công", s_c_detailsPage)
            );

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Không tìm thấy",
                            ""));
        }
    }


    @PutMapping("/findQuantity")
    public ResponseEntity<ResponseObject> findQuantity(@RequestBody FindQuantity search) {
        int quantity = 0;
        List<S_C_Details> list = service.findQuantity(search.getProduct(), search.getSize(), search.getMau());
        for (S_C_Details x : list) {
            quantity += x.getQuantity();
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(HttpStatus.OK, "Tìm thấy thành công",quantity ));
    }
@PostMapping("/congsl")
    public List<S_C_DetailDTO>congsl(@RequestBody List<S_C_DetailDTO>lstS_c_detailDTOS){
    List<S_C_DetailDTO>congsl=service.congsl(lstS_c_detailDTOS);
        return congsl;
}
    @PostMapping("/trusl")
    public List<S_C_DetailDTO>trusl(@RequestBody List<S_C_DetailDTO>lstS_c_detailDTOS){
        List<S_C_DetailDTO>trusl=service.trusl(lstS_c_detailDTOS);
        return trusl;
    }
    @PostMapping("/getsize")
    public List<SizeDTO>getsize(@RequestBody S_C_DetailDTO dto){
        List<SizeDTO>size=service.getsize(dto);
        return size;
    }
}
