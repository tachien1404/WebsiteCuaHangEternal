package webbangiaydabong.Rest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webbangiaydabong.dto.ProductDTO;
import webbangiaydabong.entity.Product;
import webbangiaydabong.service.ProductService;

@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

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
	
	
}


