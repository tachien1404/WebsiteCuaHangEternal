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
	List<AccountDTO> getAll(int page, int size);
	AccountDTO get(long id);
	List<AccountDTO> search(String keywork, String active, String role);
	Account findByUserName(String userName);

	List<Account> findAll();
	int getSize(int page, int size);
	Account findBySdt(String sdt);

	Account tao(Account account);

}
