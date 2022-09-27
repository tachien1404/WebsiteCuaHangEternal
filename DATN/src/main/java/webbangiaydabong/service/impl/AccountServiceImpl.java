package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webbangiaydabong.entity.Account;
import webbangiaydabong.repository.AccountRepository;
import webbangiaydabong.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepo;

	@Override
	public Account findById(Long id) {
		return accountRepo.findById(id).get();
	}
	
	
	

}
