package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webbangiaydabong.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
