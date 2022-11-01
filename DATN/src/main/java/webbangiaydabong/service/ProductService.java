package webbangiaydabong.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webbangiaydabong.entity.Brand;
import webbangiaydabong.entity.Category;
import webbangiaydabong.entity.Product;

public interface ProductService {

	List<Product> findAll();
	List<Product> findByCategoryId(String categoryId);
	Product findById(Long id);
	Product create(Product product);
	Product update(Product product);
	
	void delete(Long id);

	Page<Product> findByKey(Pageable pageable,
							String name,
							Long id,
							Double inportprice,
							Double outputprice,
							Category category,
							Brand hang);


}
