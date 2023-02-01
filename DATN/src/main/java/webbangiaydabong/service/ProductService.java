package webbangiaydabong.service;

import java.util.Date;
import java.util.List;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import webbangiaydabong.dto.ProductDTO;
import webbangiaydabong.entity.*;

public interface ProductService {

	List<Product> findAll();
	List<Product> findByCategoryId(String categoryId);
	Product findById(Long id);
	Product create(Product product);
	Product update(Product product);
	
	void delete(Long id);

	Page<Product> findByKey(Pageable pageable,
							String name,
							Double outputprice,
							Category category,
							Brand hang,
							Sole sole,
							ShoeLine shoeLine);

	List<Product> findByStatus();
    List<Product> findTop(Date date);
    List<ProductDTO>serchName(ProductDTO dto);
List<Product>topbanchay();
}
