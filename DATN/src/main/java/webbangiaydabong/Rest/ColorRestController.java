package webbangiaydabong.Rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import webbangiaydabong.dto.ColorDTO;
import webbangiaydabong.dto.ProductSearchDTO;
import webbangiaydabong.dto.functiondto.SortByValue;
import webbangiaydabong.entity.Account;
import webbangiaydabong.entity.Color;
import webbangiaydabong.entity.Product;
import webbangiaydabong.entity.ResponseObject;
import webbangiaydabong.service.ColorService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public/colors")
public class ColorRestController {

	@Autowired
	ColorService colorService;

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody ColorDTO request) {
		Color color = colorService.save(request);
		if (color == null) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(color, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ColorDTO request) {
		Color color = colorService.update(id, request);
		if (color == null) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(color, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			colorService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping()
	public ResponseEntity<?> getAll(@RequestParam("page") int page,
									@RequestParam("size") int size) {
		Page<Color> colors = colorService.findAlls(page, size);
		if (colors.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(colors, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Color color = colorService.findById(id);
		if (color == null) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(color, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam("keyword") String keyword){
		List<Color> colors = colorService.search(keyword);
		if (colors.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(colors, HttpStatus.OK);
	} 
}