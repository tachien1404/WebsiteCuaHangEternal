package webbangiaydabong.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webbangiaydabong.core.Constants;
import webbangiaydabong.dto.AccountDTO;
import webbangiaydabong.entity.Account;
import webbangiaydabong.entity.Roles;
import webbangiaydabong.repository.AccountRepository;
import webbangiaydabong.repository.AuthenticateRepository;
import webbangiaydabong.repository.RolesRepository;
import webbangiaydabong.service.AuthenticateService;
import webbangiaydabong.service.mapper.AccountMapper;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService {
    @Autowired
    AuthenticateRepository authenticateRepository;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    RolesRepository roleRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account signup(AccountDTO dto) {
       return null;
    }
}
