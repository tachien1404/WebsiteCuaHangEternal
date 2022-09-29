package webbangiaydabong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import webbangiaydabong.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query("select p FROM Product p WHERE p.category.id=?1")
	List<Product> finByCategoryId(String categoryId);
}
