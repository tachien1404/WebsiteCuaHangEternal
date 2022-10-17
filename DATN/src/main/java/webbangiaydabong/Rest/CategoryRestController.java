package webbangiaydabong.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.dto.CategoryDTO;
import webbangiaydabong.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/saveorupdate")
    public ResponseEntity<?> save(@RequestBody CategoryDTO dto){
        categoryService.saveOrUpdate(dto);
        return  new ResponseEntity<>( categoryService.saveOrUpdate(dto), HttpStatus.OK);
    }
    @GetMapping("/getall")
    public List<CategoryDTO>getall(){
        return categoryService.findAll();
    }
}
