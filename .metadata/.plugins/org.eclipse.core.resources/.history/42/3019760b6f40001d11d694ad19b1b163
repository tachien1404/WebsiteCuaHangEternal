package webbangiaydabong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import webbangiaydabong.entity.Category;
import webbangiaydabong.repository.CategoryRepositoty;
import webbangiaydabong.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepositoty categoryRepo;

	@Override
	public List<Category> findAll() {
		return categoryRepo.findAll();
	}
	
}
