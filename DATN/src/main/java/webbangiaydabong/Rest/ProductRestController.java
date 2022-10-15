package webbangiaydabong.Rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import webbangiaydabong.dto.ProductDTO;
import webbangiaydabong.entity.Product;
import webbangiaydabong.service.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/products")
public class ProductRestController {
@Autowired
UploadService uploadService;
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	BrandService brandService;


	@GetMapping()
	public List<Product> getAll() {
		return productService.findAll();
	}

	@GetMapping("{id}")
	public Product getOne(@PathVariable("id") Long id) {
		return productService.findById(id);
	}
	
	@PostMapping
	public Product create(@RequestBody ProductDTO dto) {
		Product entity = new Product();
		BeanUtils.copyProperties(dto, entity);
		Date date = new Date();
		entity.setCreateDate(date);
		entity.setCategory(categoryService.findById(dto.getCategory_id()));
		entity.setHang(brandService.findById(dto.getHang_id()));
		return productService.create(entity);
	}
	
	@PutMapping("{id}")
	public Product update(@PathVariable("id") Long id, @RequestBody ProductDTO dto) {
		Product entity = new Product();
		entity = productService.findById(id);
		entity.setName(dto.getName());
		entity.setInportprice(dto.getInportprice());
		entity.setOutputprice(dto.getOutputprice());
		entity.setStatus(dto.getStatus());
		Date date = new Date();
		entity.setUpdatedate(date);
		entity.setCategory(categoryService.findById(dto.getCategory_id()));
		entity.setHang(brandService.findById(dto.getHang_id()));
		return productService.update(entity);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		productService.delete(id);
	}
	//Import Excel
	@PostMapping("/importproduct")
	public ResponseEntity<?> importSinhVien(@RequestParam("file") MultipartFile uploadfile){
		try {
			
			List<ProductDTO> list = uploadService.importProducttoExcel(uploadfile);
			uploadService.savelist(list);
			return new ResponseEntity<>("Import success!", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage()+"no");
			return new ResponseEntity<>("Import failed!", HttpStatus.BAD_REQUEST);
		}
	}
	
}


