package webbangiaydabong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import webbangiaydabong.entity.Category;
import webbangiaydabong.service.CategoryService;

public class CategoryController {
	@Autowired
    CategoryService categoryService;
    
    @RequestMapping("/category/list")
    public String list(Model model ) {
    	List<Category> list = categoryService.findAll();
    	model.addAttribute("items", list);
    	return "category/list";
    	
    }
}
