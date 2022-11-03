package webbangiaydabong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import webbangiaydabong.entity.Brand;
import webbangiaydabong.entity.Category;
import webbangiaydabong.entity.Product;
import webbangiaydabong.repository.ProductRepository;
import webbangiaydabong.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepo;

	@Override
	public List<Product> findAll() {
		return productRepo.findAll();
	}

	@Override
	public List<Product> findByCategoryId(String categoryId) {
		return productRepo.finByCategoryId(categoryId);
	}

	@Override
	public Product findById(Long id) {
		return productRepo.findById(id).get();
	}

	@Override
	public Product create(Product product) {
		return productRepo.save(product);
	}

	@Override
	public Product update(Product product) {
		return productRepo.save(product);
	}

	@Override
	public void delete(Long id) {
		productRepo.deleteById(id);
		
	}

	@Override
	public Page<Product> findByKey(Pageable pageable, String name,Long id, Double inportprice, Double outputprice, Category category, Brand hang) {
		return productRepo.findByKey(pageable, name,id, inportprice, outputprice, category, hang);
	}

}
