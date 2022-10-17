package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import webbangiaydabong.dto.BrandDTO;
import webbangiaydabong.dto.CategoryDTO;
import webbangiaydabong.entity.Category;

import java.util.List;

public interface CategoryRepositoty extends JpaRepository<Category, Long> {
    @Query("select new webbangiaydabong.dto.CategoryDTO(o) from Category o ")
    List<CategoryDTO> getAllCategory();
}
