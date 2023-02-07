package webbangiaydabong.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webbangiaydabong.dto.ProductDTO;
import webbangiaydabong.entity.*;

public interface ProductService {

    List<Product> findAll();
    Product checktrung(Product p);

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

    List<ProductDTO> serchName(ProductDTO dto);

    List<Object> topbanchay();

    Page<Product> findByKey2(Pageable pageable,
                            String name,
                            Float priceStart,
                             Float priceEnd,
                            Category category,
                            Brand hang,
                            Sole sole,
                            ShoeLine shoeLine);

    List<Object> hotTrend(Long idProduct);
    
    List<ProductDTO>locproductadmin(ProductDTO dto);
}
