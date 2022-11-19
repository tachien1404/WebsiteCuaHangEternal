package webbangiaydabong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webbangiaydabong.dto.BrandDTO;
import webbangiaydabong.dto.CategoryDTO;
import webbangiaydabong.entity.Brand;
import webbangiaydabong.entity.Category;
import webbangiaydabong.entity.Product;

import java.util.List;
@Repository
public interface CategoryRepositoty extends JpaRepository<Category, Long> {
    @Query("select new webbangiaydabong.dto.CategoryDTO(o) from Category o ")
    List<CategoryDTO> getAllCategory();

    @Query("select c FROM Category c where c.delete =true ")
    List<Category> getAll();

    @Query("select c FROM Category c WHERE" +
            "(lower(c.name)  like '%' ||  lower(:name) || '%' or :name is null)" +
            " and  (c.id  = :id  or :id is null)" +
            "and  c.delete = true")
    Page<Category> findByKey(
            Pageable pageable,
            @Param("name") String name,
            @Param("id") Long id
    );

    @Query(value = "SELECT COUNT(category.id) FROM category WHERE category.name LIKE :name", nativeQuery = true)
    Integer countName(String name);
}
