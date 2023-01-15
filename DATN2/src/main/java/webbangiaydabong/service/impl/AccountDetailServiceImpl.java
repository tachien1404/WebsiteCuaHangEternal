package webbangiaydabong.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import webbangiaydabong.entity.Account;
import webbangiaydabong.repository.AccountRepository;

import javax.security.auth.login.AccountException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component("accountDetailsService")
public class AccountDetailServiceImpl implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException("username not found");
        }

        if (!account.isActive()) {
            throw new UsernameNotFoundException("User " + username + " was not activated");
        }

        List<GrantedAuthority> grantedAuthorities
                = account.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getRole().getName())).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), grantedAuthorities);
    }
}
