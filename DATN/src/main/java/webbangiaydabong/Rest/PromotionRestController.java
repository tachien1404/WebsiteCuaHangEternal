package webbangiaydabong.Rest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.dto.PromotionDTO;
import webbangiaydabong.entity.Promotion;
import webbangiaydabong.service.AccountService;
import webbangiaydabong.service.PromotionService;

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

}
