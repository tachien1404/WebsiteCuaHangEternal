package webbangiaydabong.Rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import webbangiaydabong.dto.AccountDTO;
import webbangiaydabong.dto.AccountSignUp;
import webbangiaydabong.entity.Account;
import webbangiaydabong.entity.Customer;
import webbangiaydabong.entity.CustommerInfo;
import webbangiaydabong.entity.ResponseObject;
import webbangiaydabong.repository.AccountRepository;
import webbangiaydabong.service.AccountService;
import webbangiaydabong.service.UploadService;

@RestController
@RequestMapping("/api/public/account")
public class AccountRestController {
@Autowired
AccountService accountService;

@Autowired
AccountRepository repository;

@Autowired
UploadService uploadService;

@Autowired
PasswordEncoder passwordEncoder;

@PostMapping("/dangki")
public void dangki(Model model, AccountDTO dto) {
	accountService.save(dto);
}


@PostMapping("/dangky2")
public ResponseEntity<?> save(@RequestBody AccountDTO dto) {
	dto = accountService.save(dto);
	return new ResponseEntity<>(dto, HttpStatus.OK);
}

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
public ResponseEntity<?> getAll(@RequestParam("page")int page,
		@RequestParam("size") int size){
	List<AccountDTO> accounts = accountService.getAll(page, size);
	return new ResponseEntity<>(accounts, HttpStatus.OK);
}

@GetMapping("/{id}")
public ResponseEntity<?> get(@PathVariable("id") long id){
	AccountDTO account = accountService.get(id);
	return new ResponseEntity<>(account, HttpStatus.OK);
}

@GetMapping("/search")
public ResponseEntity<?> search(@RequestParam("email") String keywork,
		@RequestParam("active") String active,
		@RequestParam("role") String role){
	List<AccountDTO> accounts = accountService.search(keywork, active, role);
	return new ResponseEntity<>(accounts, HttpStatus.OK);
<<<<<<< Updated upstream
} 

@GetMapping("/size")
public ResponseEntity<?> get(@RequestParam("page")int page,
		@RequestParam("size") int size){
	return new ResponseEntity<>(accountService.getSize(page, size), HttpStatus.OK);
=======
>>>>>>> Stashed changes
}

	@GetMapping("/size")
	public ResponseEntity<?> get(@RequestParam("page")int page,
								 @RequestParam("size") int size){
		return new ResponseEntity<>(accountService.getSize(page, size), HttpStatus.OK);
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
<<<<<<< Updated upstream

	@GetMapping("findByUserName/{userName}")
	public Account getAccountByUserName(@PathVariable("userName") String userName){
		return accountService.findByUserName(userName);
	}
=======
@PostMapping("/signUp")
	public ResponseEntity<ResponseObject> signUp(@RequestBody AccountSignUp accountSignUp){
	try{
		if (accountService.findByUserName(accountSignUp.getUsername()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseObject(HttpStatus.BAD_REQUEST,"Username da ton tai","")
			);
		}
		if (accountService.findByEmail(accountSignUp.getEmail()) != null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseObject(HttpStatus.BAD_REQUEST,"Email da ton tai","")
			);
		}
		if (accountService.findBySdt(accountSignUp.getSdt()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseObject(HttpStatus.BAD_REQUEST, "Phone đã tồn tại", ""));
		}
		Account accountnew = new Account();
		accountnew.setUsername(accountSignUp.getUsername());
		accountnew.setEmail(accountSignUp.getEmail());
		accountnew.setFullname(accountSignUp.getFullname());
		accountnew.setPassword(passwordEncoder.encode(accountSignUp.getPassword()));
		accountnew.setSdt(accountSignUp.getSdt());
		accountnew.setActive(true);
		Account dbAcc = accountService.tao(accountnew);

		CustommerInfo custommerInfonew = new CustommerInfo();
		custommerInfonew.setSdt(dbAcc.getSdt());
		custommerInfonew.setName(dbAcc.getFullname());
		custommerInfonew.setCity(accountSignUp.getCity());
		custommerInfonew.setNameCity(accountSignUp.getNameCity());
		custommerInfonew.setDistrict(accountSignUp.getDistrict());
		custommerInfonew.setNameDistrict(accountSignUp.getNameDistrict());
		custommerInfonew.setWard(accountSignUp.getWard());
		custommerInfonew.setAddress(accountSignUp.getAddress());
		custommerInfonew.setAccount(dbAcc);

		Customer customernew = new Customer();
		customernew.setSdt(dbAcc.getSdt());
		customernew.setName(dbAcc.getFullname());
		customernew.setCity(accountSignUp.getCity());
		customernew.setNameCity(accountSignUp.getNameCity());
		customernew.setDistrict(accountSignUp.getDistrict());
		customernew.setNameDistrict(accountSignUp.getNameDistrict());
		customernew.setWard(accountSignUp.getWard());
		customernew.setAddress(accountSignUp.getAddress());

		return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Tạo mới thành công", ""));

	}catch (Exception e){
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new ResponseObject(HttpStatus.BAD_REQUEST, "Tạo mới thất bại",
						""));
	}
}
>>>>>>> Stashed changes

}

