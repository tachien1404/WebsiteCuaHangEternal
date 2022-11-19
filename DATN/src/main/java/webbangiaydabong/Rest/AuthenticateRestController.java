package webbangiaydabong.Rest;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import webbangiaydabong.Rest.vm.LoginVM;
import webbangiaydabong.core.Constants;
import webbangiaydabong.dto.AccountDTO;
import webbangiaydabong.entity.Account;
import webbangiaydabong.entity.ResponseObject;
import webbangiaydabong.security.jwt.JWTFilter;
import webbangiaydabong.security.jwt.TokenProvider;
import webbangiaydabong.service.AccountService;
import webbangiaydabong.service.AuthenticateService;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping(value = Constants.Api.Path.AUTH)
@Api(tags = "Auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticateRestController {
    @Autowired
    AuthenticateService authenticateService;

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    AccountService accountService;

    @Autowired
    TokenProvider tokenProvider;


    @PostMapping("/signup")
    public ResponseEntity<Account> signup(@Valid @RequestBody AccountDTO dto) {
        return ResponseEntity.ok().body(authenticateService.signup(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginVM loginVM) {
            //		Tạo chuỗi authentication từ username và password (object LoginRequest
//		- file này chỉ là 1 class bình thường, chứa 2 trường username và password)
            UsernamePasswordAuthenticationToken authenticationString = new UsernamePasswordAuthenticationToken(
                    loginVM.getUserName(),
                    loginVM.getPassword()
            );
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationString);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication, loginVM.getRememberMe());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, String.format("Bearer %s", jwt));
            return new ResponseEntity<>(Collections.singletonMap("token", jwt), httpHeaders, HttpStatus.OK); //Trả về chuỗi jwt(authentication string)

//        User userLogin = userService.findUserByUserName(adminLoginVM.getUserName());
//        return ResponseEntity.ok().body(new JWTTokenResponse(jwt, userLogin.getUserName())); //Trả về chuỗi jwt(authentication string)


    }




}
