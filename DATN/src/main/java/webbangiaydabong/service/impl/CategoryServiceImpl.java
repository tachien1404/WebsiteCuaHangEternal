package webbangiaydabong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webbangiaydabong.dto.BrandDTO;
import webbangiaydabong.dto.CategoryDTO;
import webbangiaydabong.entity.Brand;
import webbangiaydabong.entity.Category;
import webbangiaydabong.repository.CategoryRepositoty;
import webbangiaydabong.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepositoty categoryRepo;



	@Override
	public Category findById(Long id) {
		return categoryRepo.findById(id).get();
	}

	@Override
	public List<Category> getAll() {
		return categoryRepo.getAll();
	}

	@Override
	public Page<Category> findByKey(Pageable pageable, String name, Long id) {
		return categoryRepo.findByKey(pageable, name, id);
	}

	@Override
	public Category createOrUpdate(Category category) {
		return categoryRepo.save(category);
	}

	@Override
	public CategoryDTO saveOrUpdate(CategoryDTO dto) {
		Category cate=null;
		if(dto.getId()!=null){
			cate=categoryRepo.getOne(dto.getId());
		}
		if(cate==null){
			cate=new Category();
		}
		if(dto.getName()!=null){
			cate.setName(dto.getName());
		}
		cate=categoryRepo.save(cate);
		return new CategoryDTO(cate);

	}

	@Override
	public boolean checkName(String name) {
		Long count=categoryRepo.countName(name);
		if(count>0){
			return true;
		}
		return false;
	}

	@Override
	public List<CategoryDTO> findAll() {
		return categoryRepo.getAllCategory();
	}
}
