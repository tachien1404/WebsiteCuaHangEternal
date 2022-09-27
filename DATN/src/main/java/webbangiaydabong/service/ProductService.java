package webbangiaydabong.service;

import java.util.List;

import webbangiaydabong.entity.Product;

public interface ProductService {

	List<Product> findAll();
	List<Product> findByCategoryId(Long categoryId);
	Product findById(Long id);
	Product create(Product product);
	Product update(Product product);
	
	void delete(Long id);
}
