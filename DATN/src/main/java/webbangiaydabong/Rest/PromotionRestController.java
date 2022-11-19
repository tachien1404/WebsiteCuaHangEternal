package webbangiaydabong.Rest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.dto.ProductSearchDTO;
import webbangiaydabong.dto.PromotionDTO;
import webbangiaydabong.dto.PromotionSearch;
import webbangiaydabong.dto.functiondto.SortByValue;
import webbangiaydabong.entity.Product;
import webbangiaydabong.entity.Promotion;
import webbangiaydabong.entity.ResponseObject;
import webbangiaydabong.service.AccountService;
import webbangiaydabong.service.PromotionService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/promotion")
public class PromotionRestController {

    @Autowired
    PromotionService promotionService;
    @Autowired
    AccountService accountService;

    @GetMapping()
    public List<Promotion> getAll() {
        return promotionService.findAll();
    }

    @GetMapping("{id}")
    public Promotion getOne(@PathVariable("id") Long id) {
        return promotionService.findById(id);
    }

    @PostMapping()
    public Promotion create(@RequestBody PromotionDTO dto) {
        Promotion entity = new Promotion();
        BeanUtils.copyProperties(dto, entity);
        Date date = new Date();
        entity.setCreateDate(date);
        entity.setAccount(accountService.findById(dto.getAccount_id()));
        return promotionService.create(entity);
    }

    @PutMapping("{id}")
    public Promotion update(@PathVariable("id") Long id, @RequestBody PromotionDTO dto) {
        Promotion entity = promotionService.findById(id);
        BeanUtils.copyProperties(dto, entity);
        Date date = new Date();
        entity.setUpdatetedDate(date);
        entity.setAccount(accountService.findById(dto.getAccount_id()));
        return promotionService.update(entity);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        promotionService.delete(id);
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
                                                    @RequestBody PromotionSearch promotionSearch) {
        try {
            page = page <0? 0:page;
            Pageable pageable;
            List<Sort.Order> orders = new ArrayList<>();
            List<SortByValue> sortByValueList = promotionSearch.getSortByValues();
            System.out.println(sortByValueList);
            if(sortByValueList.isEmpty()){
                orders.add(new Sort.Order(Sort.Direction.ASC,"id"){
                });
            }else {
                sortByValueList.forEach(value ->{
                    orders.add(new Sort.Order(getSortDirection(value.getType()),value.getName()));
                });
            }

            pageable = PageRequest.of(page,size,Sort.by(orders));
            Page<Promotion> promotionPage;
            promotionPage = promotionService.findByKey(pageable,promotionSearch.getName(),promotionSearch.getId(),
                    promotionSearch.getStarttime(),promotionSearch.getEndtime(),promotionSearch.getValue());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(HttpStatus.OK,"Tìm thấy thành công",promotionPage)
            );

        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Không tìm thấy",
                            ""));
        }
    };
}
