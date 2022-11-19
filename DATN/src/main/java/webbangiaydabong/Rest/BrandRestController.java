package webbangiaydabong.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.dto.BrandDTO;
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
}
