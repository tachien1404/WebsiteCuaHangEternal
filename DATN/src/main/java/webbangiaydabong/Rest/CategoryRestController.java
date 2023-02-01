package webbangiaydabong.Rest;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.dto.CategoryDTO;
import webbangiaydabong.dto.CategorySearch;
import webbangiaydabong.dto.ProductSearchDTO;
import webbangiaydabong.dto.functiondto.SortByValue;
import webbangiaydabong.entity.Brand;
import webbangiaydabong.entity.Category;
import webbangiaydabong.entity.Product;
import webbangiaydabong.entity.ResponseObject;
import webbangiaydabong.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public/category")
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
                                                    @RequestBody CategorySearch categorySearch) {
        try {
            page = page <0? 0:page;
            Pageable pageable;
            List<Sort.Order> orders = new ArrayList<>();
            List<SortByValue> sortByValueList = categorySearch.getSortByValues();
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
            Page<Category> categoryPage;
            categoryPage = categoryService.findByKey(pageable,categorySearch.getName(),categorySearch.getId());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(HttpStatus.OK,"Tìm thấy thành công",categoryPage)
            );

        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Không tìm thấy",
                            ""));
        }
    };

    @PostMapping()
    ResponseEntity<ResponseObject> create(@RequestBody Category category){
        String mess = "Cập nhật ";
        if(category.getId() ==null){
            mess  = "Thêm mới " ;
        }
        try {
         System.out.println(category.getId());
         category.setDelete(true);
         categoryService.createOrUpdate(category);
         return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK,
         mess +"thành công",""));
     } catch (Exception e){
         e.printStackTrace();
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                 new ResponseObject(HttpStatus.BAD_REQUEST,
                         mess+"thất bại", ""));
     }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> update(@PathVariable("id") Long id){
        try {
            Category cate =categoryService.findById(id);
            cate.setDelete(false);
            categoryService.createOrUpdate(cate);
            return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK,"Xóa thành công",""));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Xóa thất bại",
                            ""));
        }


    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> quickAdd(@RequestBody Category category){
        if(categoryService.getByName(category.getName()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST,
                            "Danh mục đã tồn tại", ""));
        }
        category.setDelete(true);
        categoryService.createOrUpdate(category);
        return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK,
                "Tạo mới danh mục thành công",""));
    }
}
