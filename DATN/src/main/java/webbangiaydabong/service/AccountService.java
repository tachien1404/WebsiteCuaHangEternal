package webbangiaydabong.service;

import java.util.List;

import webbangiaydabong.dto.AccountDTO;
import webbangiaydabong.entity.Account;

public interface AccountService {
	Account findById(Long id);
	AccountDTO save(AccountDTO dto);
	Account findByEmail(String email);
	AccountDTO create(AccountDTO dto);
	AccountDTO update(long id, AccountDTO dto);
	void delete(long id);
	List<Account> getAll(int page);
	Account get(long id);
	List<Account> search(String keywork);
	Account findByUserName(String userName);

}
