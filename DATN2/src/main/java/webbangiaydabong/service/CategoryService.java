package webbangiaydabong.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webbangiaydabong.dto.CategoryDTO;
import webbangiaydabong.entity.Category;

public interface CategoryService {
CategoryDTO saveOrUpdate(CategoryDTO dto);
	List<CategoryDTO> findAll();
	Category findById(Long id);
	List<Category> getAll();

	Page<Category> findByKey(
			Pageable pageable,
			String name,
			Long id
	);

	Category createOrUpdate(Category category);

    boolean checkName(String name);
}
