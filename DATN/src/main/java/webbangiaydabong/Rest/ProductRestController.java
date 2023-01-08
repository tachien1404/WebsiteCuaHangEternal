package webbangiaydabong.Rest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import webbangiaydabong.dto.ProductDTO;
import webbangiaydabong.dto.ProductSearchDTO;
import webbangiaydabong.dto.functiondto.SortByValue;
import webbangiaydabong.entity.*;
import webbangiaydabong.service.*;

@RestController
@CrossOrigin("*" )
@RequestMapping("/api/public/products")
public class ProductRestController {
    @Autowired
    UploadService uploadService;
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BrandService brandService;

    @Autowired
    SizeService sizeService;

    @Autowired
    ColorService colorService;

    @Autowired
    SoleService soleService;



    @GetMapping()
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("{id}")
    public Product getOne(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<ResponseObject> create(@RequestBody Product product) {
        Date date = new Date();
        product.setCreateDate(date);
        product.setStatus(1);
        product.setDelete(true);
        productService.create(product);
        return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Thêm mới sản phẩm thành công", ""));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseObject> update(@PathVariable("id") Long id, @RequestBody Product product) {
//        Product entity = new Product();
//        entity = productService.findById(id);
//        entity.setName(dto.getName());
//        entity.setInportprice(dto.getInportprice());
//        entity.setOutputprice(dto.getOutputprice());
//        entity.setStatus(dto.getStatus());
//        Date date = new Date();
//        entity.setUpdatedate(date);
//        entity.setCategory(categoryService.findById(dto.getCategory_id()));
//        entity.setHang(brandService.findById(dto.getHang_id()));
//        return productService.update(entity);
       try{
           Date date = new Date();
           product.setUpdatedate(date);
           productService.update(product);
           return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Cập nhật sản phẩm thành công", ""));
       } catch (Exception e){
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                   new ResponseObject(HttpStatus.BAD_REQUEST, "Cập nhật thất bại",
                           ""));
       }

    }

    @GetMapping("/delete/{id}")
    public  ResponseEntity<ResponseObject> delete(@PathVariable("id") Long id) {
//        productService.delete(id);
        try {
            Product p =productService.findById(id);
            p.setDelete(false);
            productService.update(p);
            return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK,"Xóa thành công",""));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Xóa thất bại",
                            ""));
        }

    }

    //Import Excel
    @PostMapping("/importproduct")
    public ResponseEntity<?> importSinhVien(@RequestParam("folder") MultipartFile uploadfile) {
        try {

            List<ProductDTO> list = uploadService.importProducttoExcel(uploadfile);
            uploadService.savelist(list);
            return new ResponseEntity<>("Import success!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage() );
            return new ResponseEntity<>("Import failed!", HttpStatus.BAD_REQUEST);
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
													@RequestBody ProductSearchDTO dto) {
    	try {
			page = page <0? 0:page;
			Pageable pageable;
			List<Sort.Order> orders = new ArrayList<>();
			List<SortByValue> sortByValueList = dto.getSortByValues();
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
			Page<Product> productPage;
			productPage = productService.findByKey(pageable,dto.getName(),dto.getId(),
					                              dto.getOutputprice(),dto.getCategory(),dto.getHang());
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject(HttpStatus.OK,"Tìm thấy thành công",productPage)
			);

		} catch (Exception e){
    		e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseObject(HttpStatus.BAD_REQUEST, "Không tìm thấy",
							""));
		}
    };

    @GetMapping("/getAllCategory")
    public List<Category> getAllCategory(){
        return categoryService.getAll();
    }

    @GetMapping("/getAllBrand")
    public List<Brand> getAllBrand(){
        return brandService.getAll();
    }

    @GetMapping("/getAllSize")
    public List<size> getAllSize(){
        return sizeService.findAll();
    }

    @GetMapping("/getColor")
    public List<Color> getColor(){
        return colorService.findAll();
    }

    @GetMapping("/getAllSole")
    public List<Sole> getAllSoleActive() {
        return soleService.findAllActice();
    }

   

    @GetMapping("/top/{top}")
    public List<Product> findTop(@PathVariable("top") Integer top){
      List<Product> productsTop = new ArrayList<>();
      Date date = new Date();
      List<Product> productsDb = productService.findTop(date);
      for(int i=0;i<top;i++){
          productsTop.add(productsDb.get(i));
      }
      return productsTop;
    }

    @PostMapping("/image")
    public HttpStatus upload(@RequestParam("file") MultipartFile multipartFile){
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        try {
        	System.out.println("oke");
            uploadService.saveProduct("image",fileName, multipartFile);
            return HttpStatus.OK;
        } catch (IOException e) {
            e.printStackTrace();
            return HttpStatus.CONFLICT;
        }
        
    }

    @PostMapping ("/serchName")
    public List<ProductDTO> serch(@RequestBody ProductDTO dto){

        return productService.serchName(dto);
    }

}





