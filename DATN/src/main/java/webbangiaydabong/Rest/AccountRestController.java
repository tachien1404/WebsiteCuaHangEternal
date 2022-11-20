package webbangiaydabong.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webbangiaydabong.dto.AccountDTO;
<<<<<<< Updated upstream
=======
import webbangiaydabong.entity.Account;
import webbangiaydabong.repository.AccountRepository;
>>>>>>> Stashed changes
import webbangiaydabong.service.AccountService;

@RestController
@RequestMapping("/api/account/")
public class AccountRestController {
@Autowired
AccountService accountService;
<<<<<<< Updated upstream
=======

@Autowired
AccountRepository repository;

@Autowired
UploadService uploadService;

>>>>>>> Stashed changes
@PostMapping("/dangki")
public ResponseEntity<?>dangki(@RequestBody AccountDTO dto){
		return new ResponseEntity<>(accountService.save(dto),HttpStatus.OK);
}
}
<<<<<<< Updated upstream
=======

@PostMapping("/")
public ResponseEntity<?> create(@RequestBody AccountDTO dto) {
	dto = accountService.create(dto);
	return new ResponseEntity<>(dto, HttpStatus.OK);
}

@PutMapping("/{id}")
public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody AccountDTO dto) {
	dto = accountService.update(id, dto);
	return new ResponseEntity<>(dto, HttpStatus.OK);
}

@DeleteMapping("/{id}")
public ResponseEntity<?> delete(@PathVariable("id") long id) {
	accountService.delete(id);
	return new ResponseEntity<>(HttpStatus.OK);
}

@GetMapping("/")
public ResponseEntity<?> getAll(@RequestParam("page")int page ){
	List<Account> accounts = accountService.getAll(page);
	return new ResponseEntity<>(accounts, HttpStatus.OK);
}

@GetMapping("/{id}")
public ResponseEntity<?> get(@PathVariable("id") long id){
	Account account = accountService.get(id);
	return new ResponseEntity<>(account, HttpStatus.OK);
}

@GetMapping("/search")
public ResponseEntity<?> search(@RequestParam("email") String keywork){
	List<Account> accounts = accountService.search(keywork);
	return new ResponseEntity<>(accounts, HttpStatus.OK);
} 

@GetMapping("/size")
public ResponseEntity<?> get(){
	return new ResponseEntity<>(repository.findAll().size(), HttpStatus.OK);
}

@PostMapping("/image")
public HttpStatus upload(@RequestParam("file") MultipartFile multipartFile){
    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    try {
        uploadService.save("image",fileName, multipartFile);
        return HttpStatus.OK;
    } catch (IOException e) {
        e.printStackTrace();
        return HttpStatus.CONFLICT;
    }
}
}

>>>>>>> Stashed changes
