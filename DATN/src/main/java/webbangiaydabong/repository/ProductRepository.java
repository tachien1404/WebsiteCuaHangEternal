package webbangiaydabong.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import webbangiaydabong.dto.ProductDTO;
import webbangiaydabong.entity.*;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p FROM Product p WHERE p.category.id=?1")
    List<Product> finByCategoryId(String categoryId);

    @Query("select p FROM Product p WHERE" +
            " (lower(p.name)  like '%' ||  lower(:name) || '%' or :name is null)" +
            " and  (p.outputprice  = :outputprice  or :outputprice is null)" +
            "and   (p.category  =:category or :category is null)" +
            "and   (p.hang  =:hang or :hang is null)" +
            "and   (p.sole  =:sole or :sole is null)" +
            "and   (p.shoeLine  =:shoeLine or :shoeLine is null)" +
            "and  p.delete = true")
    Page<Product> findByKey(
            Pageable pageable,
            @Param("name") String name,
            @Param("outputprice") Double outputprice,
            @Param("category") Category category,
            @Param("hang") Brand hang,
            @Param("sole") Sole sole,
            @Param("shoeLine") ShoeLine shoeLine
    );

    @Query("select p FROM Product  p WHERE p.delete =true ")
    List<Product> findByStatus();

    @Query("select p from Product p where p.createDate <= ?1 ORDER BY p.createDate DESC")
    List<Product> findTop(Date date);

    @Query("select new webbangiaydabong.dto.ProductDTO(o) from Product o where o.name like :name")
    List<ProductDTO> serchName(String name);

    @Query("Select  o.product , SUM(o.quantity) \n "+
            "from  OrderDetail  o\n" +
            "group by o.product.name\n" +
            "order by SUM(o.quantity) desc")
    List<Object> topbanchay();
    @Query("SELECT s.product.id ,SUM(s.quantity)  \n" +
            "FROM  S_C_Details s\n" +
            "GROUP BY s.product.id")
        List<Object[]>adminproduct();
}
