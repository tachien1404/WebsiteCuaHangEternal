package webbangiaydabong.Rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webbangiaydabong.entity.Color;
import webbangiaydabong.entity.Sole;
import webbangiaydabong.service.SoleService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public/soles")
public class SoleRestController {

	@Autowired
	SoleService soleService;

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody Sole request) {
		Sole sole = soleService.create(request);
		if (sole == null) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(sole, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Sole request) {
		Sole sole = soleService.update(id, request);
		if (sole == null) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(sole, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			soleService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping()
	public ResponseEntity<?> getAll(@RequestParam("page") int page,
									@RequestParam("size") int size) {
		Page<Sole> soles = soleService.findAll(page, size);
		if (soles.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(soles, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Sole sole = soleService.find(id);
		if (sole == null) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(sole, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam("keyword") String keyword) {
		List<Sole> soles = soleService.search(keyword);
		if (soles.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(soles, HttpStatus.OK);
	}
	@GetMapping("/getAll")
	public List<Sole>getAlll(){
		List<Sole>getall=soleService.getall();
		return getall;
	}
}
