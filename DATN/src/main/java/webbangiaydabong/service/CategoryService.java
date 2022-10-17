package webbangiaydabong.service;

import java.util.List;

import webbangiaydabong.dto.CategoryDTO;
import webbangiaydabong.entity.Category;

public interface CategoryService {
CategoryDTO saveOrUpdate(CategoryDTO dto);
	List<CategoryDTO> findAll();
	Category findById(Long id);
}
