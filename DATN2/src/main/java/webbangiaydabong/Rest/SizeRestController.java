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

import webbangiaydabong.dto.SizeDTO;
import webbangiaydabong.entity.Color;
import webbangiaydabong.entity.Size;
import webbangiaydabong.service.SizeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public/sizes")
public class SizeRestController {

	@Autowired
	SizeService sizeService;

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody SizeDTO request) {
		Size size  = sizeService.save(request);
		if (size == null) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(size, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody SizeDTO request) {
		Size size = sizeService.update(id, request);
		if (size == null) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(size, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			sizeService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping()
	public ResponseEntity<?> getAll(@RequestParam("page") int page,
									@RequestParam("size") int size) {
		Page<Size> sizes = sizeService.findAlls(page, size);
		if (sizes.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(sizes, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Size color = sizeService.findById(id);
		if (color == null) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(color, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam("keyword") String keyword){
		List<Size> sizes = sizeService.search(keyword);
		if (sizes.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(sizes, HttpStatus.OK);
	} 
	
	
}
