package webbangiaydabong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import webbangiaydabong.entity.Category;
import webbangiaydabong.entity.Product;
import webbangiaydabong.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/category/list")
	public String list(Model model) {
			List<Category> list = categoryService.findAll();
			model.addAttribute("items",list);
		return "category/list";
	}

	@RequestMapping("/category/detail/{id}")
	public String detai(Model model, @PathVariable("id") Long id) {
		Category item = categoryService.findById(id);
		model.addAttribute("item",item);
		return "category/detail";
	}
}
