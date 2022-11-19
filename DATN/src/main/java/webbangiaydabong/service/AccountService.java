package webbangiaydabong.service;

import java.util.List;

import webbangiaydabong.dto.AccountDTO;
import webbangiaydabong.entity.Account;

public interface AccountService {
	 Account findById(Long id);
	 AccountDTO save(AccountDTO dto);
	 Account findByEmail(String email);
	 Account findByUsernameandEmail(String email,String username);
	 AccountDTO create(AccountDTO dto);
	 AccountDTO update(long id, AccountDTO dto);
	 void delete(long id);
	 List<Account> getAll();
	 Account get(long id);
	 List<Account> search(String keywork);
}
