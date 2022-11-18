package webbangiaydabong.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webbangiaydabong.dto.AccountDTO;
import webbangiaydabong.service.AccountService;

@RestController
@RequestMapping("api/public/account/")
public class AccountRestController {
@Autowired
AccountService accountService;
@PostMapping("/dangki")
public ResponseEntity<?>dangki(@RequestBody AccountDTO dto){
		return new ResponseEntity<>(accountService.save(dto),HttpStatus.OK);
}
}
