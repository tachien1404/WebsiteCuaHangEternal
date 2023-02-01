package webbangiaydabong.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.dto.BrandDTO;
import webbangiaydabong.entity.Brand;
import webbangiaydabong.entity.ResponseObject;
import webbangiaydabong.service.BrandService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public/brand")
public class BrandRestController {
    @Autowired
    BrandService brandService;
    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?>save(@RequestBody BrandDTO dto){
      dto=  brandService.saveOrUpdate(dto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public List<BrandDTO>getAll(){
       return brandService.getAllBrandDtos();
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> create(@RequestBody Brand brand){
        if(brandService.getByName(brand.getName()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST,
                            "Hãng đã tồn tại", ""));
        }
        brand.setDelete(true);
        brandService.create(brand);
        return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK,
                "Tạo mới hãng thành công",""));
    }
}
