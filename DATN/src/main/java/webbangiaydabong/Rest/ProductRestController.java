package webbangiaydabong.Rest;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import webbangiaydabong.dto.ProductDTO;
import webbangiaydabong.entity.Product;
import webbangiaydabong.service.ProductService;
import webbangiaydabong.service.UploadService;
import webbangiaydabong.service.impl.UploadServiceImpl;

@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
@Autowired
UploadService uploadService;
	@Autowired
	ProductService productService;
	
	@GetMapping("{id}")
	public Product getOne(@PathVariable("id") Long id) {
		return productService.findById(id);
	}
	
	@PostMapping
	public Product create(@RequestBody ProductDTO dto) {
		Product entity = new Product();
		BeanUtils.copyProperties(dto, entity);
		return productService.create(entity);
	}
	
	@PutMapping("{id}")
	public Product update(@PathVariable("id") Long id, @RequestBody ProductDTO dto) {
		Product entity = new Product();
		BeanUtils.copyProperties(dto, entity);
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


