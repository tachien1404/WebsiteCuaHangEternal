package webbangiaydabong.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import webbangiaydabong.entity.Brand;
import webbangiaydabong.entity.Category;
import webbangiaydabong.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p FROM Product p WHERE p.category.id=?1")
    List<Product> finByCategoryId(String categoryId);

    @Query("select p FROM Product p WHERE" +
            " (lower(p.name)  like '%' ||  lower(:name) || '%' or :name is null)" +
            " and  (p.id  = :id  or :id is null)" +

            " and  (p.outputprice  = :outputprice  or :outputprice is null)" +
            "and   (p.category  =:category or :category is null)" +
            "and   (p.hang  =:hang or :hang is null)" +
            "and  p.delete = true")
    Page<Product> findByKey(
            Pageable pageable,
            @Param("name") String name,
            @Param("id") Long id,

            @Param("outputprice") Double outputprice,
            @Param("category") Category category,
            @Param("hang") Brand hang
    );

    @Query("select p FROM Product  p WHERE p.delete =true ")
    List<Product> findByStatus();

    @Query("select p from Product p where p.createDate <= ?1" +
            "ORDER BY p.createDate DESC")
    List<Product> findTop(Date date);

}
