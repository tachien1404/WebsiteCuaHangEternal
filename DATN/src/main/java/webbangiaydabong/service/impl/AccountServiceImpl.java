package webbangiaydabong.service.impl;

import java.util.List;

import org.hibernate.engine.jdbc.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		acc.setUsername(dto.getUsername());
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



	@Override
	public AccountDTO update(long id, AccountDTO dto) {
		Account acc = accountRepo.findById(id).get();
		acc.setAddress(dto.getAddress());
		acc.setBirthday(dto.getBirthday());
		acc.setEmail(dto.getEmail());
		acc.setUsername(dto.getUsername());
		acc.setFullname(dto.getFullname());
		acc.setPassword(dto.getPassword());
		acc.setSdt(dto.getSdt());
		acc.setPhoto(dto.getPhoto());
		acc=accountRepo.save(acc);
		return new AccountDTO(acc);
	}

	@Override
	public void delete(long id) {
		Account acc = accountRepo.findById(id).get();
		accountRepo.delete(acc);
	}

	@Override
	public List<Account> getAll(int page) {
		Pageable pageable = PageRequest.of(page, 5);
		Page<Account> accountspage = accountRepo.findAll(pageable);
		List<Account> accounts = accountspage.getContent();
		return accounts;
	}

	@Override
	public Account get(long id) {
		return accountRepo.findById(id).get();
	}

	@Override
	public AccountDTO create(AccountDTO dto) {
		if (accountRepo.existsByUsername(dto.getUsername())) {
			return null;
		}
		if (accountRepo.existsByEmail(dto.getEmail())) {
			return null;
		}
		Account acc= new Account();
		acc.setAddress(dto.getAddress());
		acc.setUsername(dto.getUsername());
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
	public List<Account> search(String keywork) {
		List<Account> accounts = accountRepo.findByEmailLike(keywork);
		return accounts;
	}

	@Override
	public Account findByUserName(String userName) {
		return accountRepo.findByUsername(userName);
	}

	@Override
	public List<Account> findAll() {
		return accountRepo.findAll();
	}


}
