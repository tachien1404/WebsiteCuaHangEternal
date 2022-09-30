package webbangiaydabong.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import webbangiaydabong.entity.CustommerInfo;

import webbangiaydabong.service.CustommerInfoServie;

@Controller
public class CustommerInfoController {
	@Autowired
	CustommerInfoServie CusService;
	@RequestMapping("/custommerinfo/andress/{id}")
	public String detai(Model model, @PathVariable("id") Integer id) {
		CustommerInfo item = CusService.findById(id);
		model.addAttribute("item",item);
		return "custommer/andress";
	}
	
}
