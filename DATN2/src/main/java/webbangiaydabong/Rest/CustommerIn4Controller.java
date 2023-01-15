package webbangiaydabong.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.dto.CustommerInfoDTO;
import webbangiaydabong.dto.CustommerInfoSearch;
import webbangiaydabong.dto.ProductSearchDTO;
import webbangiaydabong.entity.Account;
import webbangiaydabong.entity.CustommerInfo;
import webbangiaydabong.entity.ResponseObject;
import webbangiaydabong.service.AccountService;
import webbangiaydabong.service.CustommerInfoServie;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public/cusI4/")
public class CustommerIn4Controller {

    @Autowired
    CustommerInfoServie custommerInfoServie;

    @Autowired
    AccountService accountService;

    @GetMapping("{userName}")
    public List<CustommerInfo> findAll(@PathVariable("userName") String userName) {
        return custommerInfoServie.findAllByAccount(userName);
    }

    @GetMapping("active/{userName}")
    public List<CustommerInfo> findByActive(@PathVariable("userName") String userName) {
        return custommerInfoServie.findByActive(userName);
    }
@PostMapping("/edit")
public CustommerInfoDTO edit(@RequestBody CustommerInfoDTO dto){
        return custommerInfoServie.edit(dto);
}
    @PostMapping("{userName}")
    public ResponseEntity<?> create(@PathVariable("userName") String userName,
                                    @RequestBody CustommerInfo custommerInfo) {
        try {
            Account account = accountService.findByUserName(userName);
            custommerInfo.setAccount(account);
            custommerInfo.setActive(true);
            custommerInfo.setDeafault(false);
            custommerInfoServie.create(custommerInfo);
            return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Tạo địa chỉ liên hệ thành công","" ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST,
                            "Tạo địa chỉ liên hệ thất bại", ""));
        }
    }

    @PutMapping("/findByKey")
    public ResponseEntity<ResponseObject> sortByKey(@RequestParam String id,
                                                    @RequestParam int page,
                                                    @RequestParam int size,
                                                    @RequestBody CustommerInfoSearch search) {
        Account account = accountService.findByUserName(id);
        try {
            page = page < 0 ? 0 : page;
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
            Page<CustommerInfo> custommerInfoPage = custommerInfoServie.findByKey(pageable, search.getName(), search.getSdt(),
                    search.getAddress(), account);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(HttpStatus.OK, "Tìm thấy thành công", custommerInfoPage));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Không tìm thấy",
                            ""));
        }
    }


    @GetMapping("delete/{id}")
    public ResponseEntity<?> create(@PathVariable("id") Long id) {
        try {
            CustommerInfo custommerInfo = custommerInfoServie.findById(id);
            custommerInfo.setActive(false);
            custommerInfoServie.create(custommerInfo);
            return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Xóa địa chỉ liên hệ thành công", ""));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST,
                            "Xóa địa chỉ liên hệ thất bại", ""));
        }
    }


    @GetMapping("custommerDefault")
    public CustommerInfo findAccountLoginByUserName(@RequestParam("userName") String userName){
        return custommerInfoServie.findCustommerDefalut(userName);
    }

    @GetMapping("findById")
    public CustommerInfo findAccountLoginByUserName(@RequestParam("id") Long id){
        return custommerInfoServie.findById(id);
    }


}
