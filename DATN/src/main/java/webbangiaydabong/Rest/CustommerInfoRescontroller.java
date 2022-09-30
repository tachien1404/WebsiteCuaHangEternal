package webbangiaydabong.Rest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webbangiaydabong.dto.CustommerInfoDTO;

import webbangiaydabong.entity.CustommerInfo;

import webbangiaydabong.service.CustommerInfoServie;

@RestController
@RequestMapping("/rest/custommerinfo")
public class CustommerInfoRescontroller {
	@Autowired
	CustommerInfoServie Cuservice;
	@GetMapping("{id}")
	public CustommerInfo getOne(@PathVariable("id") Integer id) {
		return Cuservice.findById(id);
	}
	
	@PostMapping
	public CustommerInfo create(@RequestBody CustommerInfoDTO dto) {
		CustommerInfo entity = new CustommerInfo();
		BeanUtils.copyProperties(dto, entity);
		return Cuservice.create(entity);
	}
	
	@PutMapping("{id}")
	public CustommerInfo update(@PathVariable("id") Integer id, @RequestBody CustommerInfoDTO dto) {
		CustommerInfo entity = new CustommerInfo();
		BeanUtils.copyProperties(dto, entity);
		return Cuservice.update(entity);
	}
	
}
