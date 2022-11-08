package webbangiaydabong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webbangiaydabong.dto.AccountDTO;
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

	@Override
	public AccountDTO save(AccountDTO dto) {// cos id thif suarw ko thif theem 
		Account acc=null;
		if(dto.getId()!=null) {
			accountRepo.getById(dto.getId());
		}
		if(acc==null) {
			acc=new Account();
		}
		acc.setAddress(dto.getAddress());
		acc.setBirthday(dto.getBirthday());
		acc.setEmail(dto.getEmail());
		acc.setFullname(dto.getFullname());
		acc.setPassword(dto.getPassword());
		acc.setSdt(dto.getSdt());
		acc.setPhoto(dto.getPhoto());
		acc=accountRepo.save(acc);
		return new AccountDTO(acc);
	}

	@Override
	public Account findByEmail(String email) {
		return accountRepo.findByEmail(email);
	}


}
