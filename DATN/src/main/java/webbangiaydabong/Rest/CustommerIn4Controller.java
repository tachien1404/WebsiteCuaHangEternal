package webbangiaydabong.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.entity.CustommerInfo;
import webbangiaydabong.entity.ResponseObject;
import webbangiaydabong.service.CustommerInfoServie;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public/cusI4/")
public class CustommerIn4Controller {

    @Autowired
    CustommerInfoServie custommerInfoServie;

    @GetMapping("{userName}")
    public List<CustommerInfo> findAll(@PathVariable("userName") String userName){
        return custommerInfoServie.findAllByAccount(userName);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CustommerInfo custommerInfo){
        try {
            custommerInfoServie.create(custommerInfo);
            return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Tạo địa chỉ liên hệ thành công", ""));
        }catch (Exception e){
            e.printStackTrace();
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST,
                            "Tạo địa chỉ liên hệ thất bại", ""));
        }
    }
}
