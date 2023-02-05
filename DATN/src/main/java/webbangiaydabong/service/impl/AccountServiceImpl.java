package webbangiaydabong.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.hibernate.engine.jdbc.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import webbangiaydabong.dto.AccountDTO;
import webbangiaydabong.entity.Account;
import webbangiaydabong.entity.Authority;
import webbangiaydabong.repository.AccountRepository;
import webbangiaydabong.repository.AuthorityRepository;
import webbangiaydabong.repository.RolesRepository;
import webbangiaydabong.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	RolesRepository rolesRepository;

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
		acc.setActive(true);
		acc=accountRepo.save(acc);
		
		Authority authority = new Authority();
		authority.setAccount(acc);


		if (dto.isRole()) {
			authority.setRole(rolesRepository.findByNameLike("ROLE_ADMIN").get());
		} else {
			authority.setRole(rolesRepository.findByNameLike("ROLE_USER").get());
					
		}
		authorityRepository.save(authority);
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
		acc.setActive(dto.isActive());
		acc=accountRepo.save(acc);
		
		Authority authority = authorityRepository.findByAccountId(id);
		authority.setAccount(acc);
		if (dto.isRole()) {
			authority.setRole(rolesRepository.findByNameLike("ROLE_ADMIN").get());
		} else {
			authority.setRole(rolesRepository.findByNameLike("ROLE_USER").get());
		}
		authorityRepository.save(authority);
		
		acc = accountRepo.findById(acc.getId()).get();
		return new AccountDTO(acc);
		
	}

	@Override
	public void delete(long id) {
		Account acc = accountRepo.findById(id).get();
		accountRepo.delete(acc);
	}

	@Override
	public List<AccountDTO> getAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
		Page<Account> accountspage = accountRepo.findAll(pageable);
		List<Account> accounts = accountspage.getContent();
		List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>();
		for (Account account : accounts) {
			accountDTOs.add(new AccountDTO(account));
		}
		return accountDTOs;
	}

	@Override
	public AccountDTO get(long id) {
		return new AccountDTO(accountRepo.findById(id).get());
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
		acc.setActive(true);
		acc=accountRepo.save(acc);
		
		Authority authority = new Authority();
		authority.setAccount(acc);
		if (dto.isRole()) {
			authority.setRole(rolesRepository.findByNameLike("ROLE_ADMIN").get());
		} else {
			authority.setRole(rolesRepository.findByNameLike("ROLE_USER").get());
					
		}
		authorityRepository.save(authority);
		
		acc = accountRepo.findById(acc.getId()).get();
		
		return new AccountDTO(acc);
	}

	@Override
	public List<AccountDTO> search(String keywork, String active, String role) {
		
		List<Account> accounts = accountRepo
				.findByEmailLikeOrFullnameLikeOrUsernameLike(
						"%" +keywork + "%", "%" +keywork + "%", "%" +keywork + "%");
		List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>();
		for (Account account : accounts) {
			AccountDTO accountDTO = new AccountDTO(account);
			if (role.equals("all")) {
				accountDTOs.add(accountDTO);
			} else if (role.equals("true")) {
				if (accountDTO.isRole()) {
					accountDTOs.add(accountDTO);
				}
			} else {
				if (!accountDTO.isRole()) {
					accountDTOs.add(accountDTO);
				}
			}
		}
		for (AccountDTO account : accountDTOs) {
			if (active.equals("true")) {
				if (!account.isActive()) {
					accountDTOs.remove(account);
				}
			} else if (active.equals("false")) {
				if (account.isActive()) {
					accountDTOs.remove(account);
				}
			} 
		}
		return accountDTOs;
	}

	@Override
	public Account findByUserName(String userName) {
		return accountRepo.findByUsername(userName);
	}

	@Override
	public List<Account> findAll() {
		return accountRepo.findAll();
	}

	@Override
	public int getSize(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Account> accountspage = accountRepo.findAll(pageable);
		return accountspage.getTotalPages();
	}


}
