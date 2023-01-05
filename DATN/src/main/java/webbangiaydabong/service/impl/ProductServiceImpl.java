package webbangiaydabong.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import webbangiaydabong.dto.ProductDTO;
import webbangiaydabong.entity.*;
import webbangiaydabong.repository.ProductRepository;
import webbangiaydabong.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepo;

	@Override
	public List<Product> findAll() {
		return productRepo.findByStatus();
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
	public Page<Product> findByKey(Pageable pageable, String name, Double outputprice, Category category, Brand hang, Sole sole, ShoeLine shoeLine) {
		return productRepo.findByKey(pageable, name, outputprice, category, hang, sole, shoeLine);
	}

	@Override
	public List<Product> findByStatus() {
		return productRepo.findByStatus();
	}

	@Override
	public List<Product> findTop(Date date) {
		return productRepo.findTop(date);
	}

	@Override
	public List<ProductDTO> serchName(String name) {
		if(name!=null){
			List<ProductDTO>lstProductDTOS=productRepo.serchName('%'+name+'%');
			return lstProductDTOS;
		}
		return null;
	}
}
