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
import webbangiaydabong.dto.*;
import webbangiaydabong.dto.functiondto.SortByValue;
import webbangiaydabong.entity.Promotion;
import webbangiaydabong.entity.PromotionDetails;
import webbangiaydabong.entity.ResponseObject;
import webbangiaydabong.service.AccountService;
import webbangiaydabong.service.PromotionDetaitlsService;
import webbangiaydabong.service.PromotionService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/public/promotion")
public class PromotionRestController {

    @Autowired
    PromotionService promotionService;
    @Autowired
    AccountService accountService;
    @Autowired
    PromotionDetaitlsService promotionDetaitlsService;

    @GetMapping()
    public List<Promotion> getAll() {
        return promotionService.findAll();
    }

    @GetMapping("{id}")
    public Promotion getOne(@PathVariable("id") Long id) {
        return promotionService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<ResponseObject> create(@RequestBody PromotionDetailDTO promotionDetailDTO) {
        Date date = new Date();
        if(promotionDetailDTO.getStarttime().before(date) ==true){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST,"Ngày bắt đầu không được nhỏ hơn ngày hiện tại",""));
        }
        Promotion promotion = new Promotion();
        promotion.setId(promotionDetailDTO.getId());
        promotion.setName(promotionDetailDTO.getName());
        promotion.setStarttime(promotionDetailDTO.getStarttime());
        promotion.setEndtime(promotionDetailDTO.getEndtime());
        promotion.setValue(promotionDetailDTO.getValue());
        promotion.setStarttime(promotionDetailDTO.getStarttime());
        promotion.setCreateDate(date);
        Promotion promotionDb =  promotionService.create(promotion);


        for(int i = 0;i<promotionDetailDTO.getListProduct().size();i++){
          PromotionDetails db =   promotionDetaitlsService.findByProduct(promotionDetailDTO.getListProduct().get(i).getId());
         if(db!= null){
             Date dateDaco1 = db.getPromotion().getStarttime();
             Date dateDaco2 = db.getPromotion().getEndtime();
             Date dateTaoMoi1 = promotionDb.getStarttime();
             Date dateTaoMoi2 = promotionDb.getEndtime();
             if((dateDaco1.before(dateTaoMoi1) && dateTaoMoi1.before(dateDaco2) ==true)
                     || (dateDaco1.before(dateTaoMoi2) && dateTaoMoi2.before(dateDaco2)==true)
                     || (dateTaoMoi1.before(dateDaco1) && dateDaco2.before(dateTaoMoi2)) ==true
                     || (dateDaco1.before(dateTaoMoi1) && dateTaoMoi2.before(dateDaco2) ==true)) {
                 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                         new ResponseObject(HttpStatus.BAD_REQUEST,"Sản phẩm " + db.getProduct().getName()+" đã có trong đợt khuyến mãi "+ db.getPromotion().getName(),""));
             }
         }
        }


         List<PromotionDetails> promotionDetailsList = new ArrayList<>();
       for(int i = 0;i<promotionDetailDTO.getListProduct().size();i++){
           PromotionDetails promotionDetails = new PromotionDetails();
           promotionDetails.setPromotion(promotionDb);
           promotionDetails.setProduct(promotionDetailDTO.getListProduct().get(i));
           promotionDetailsList.add(promotionDetails);
       }
       promotionDetaitlsService.saveAll(promotionDetailsList);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(HttpStatus.OK,"Thành công","")
        );
    }

    @PutMapping("{id}")
    public Promotion update(@PathVariable("id") Long id, @RequestBody PromotionDTO dto) {
        Promotion entity = promotionService.findById(id);
        BeanUtils.copyProperties(dto, entity);
        Date date = new Date();
        entity.setUpdatetedDate(date);
      //  entity.setAccount(accountService.findById(dto.getAccount_id()));
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
