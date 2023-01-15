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
@RequestMapping("/api/custommerinfo")
public class CustommerInfoRescontroller {
	@Autowired
	CustommerInfoServie Cuservice;
	
//	@GetMapping
//	public ResponseEntity<List<CustommerInfo>> getall() {
//		return  ResponseEntity.ok(Cuservice.findAll());
//	}
	
	@GetMapping("{id}")
	public CustommerInfo  getOne(@PathVariable("id") Long id) {
		return Cuservice.findById(id);
	}
	
	@PostMapping("/save")
	public CustommerInfoDTO Crud(@RequestBody CustommerInfoDTO dto) {
		return Cuservice.edit(dto);
	}
	
	
}
