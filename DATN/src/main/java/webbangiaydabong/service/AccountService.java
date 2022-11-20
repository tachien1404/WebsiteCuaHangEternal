	package webbangiaydabong.service;

<<<<<<< Updated upstream
=======
import java.util.List;

import org.springframework.data.domain.Page;

>>>>>>> Stashed changes
import webbangiaydabong.dto.AccountDTO;
import webbangiaydabong.entity.Account;

public interface AccountService {
<<<<<<< Updated upstream
 Account findById(Long id);
 AccountDTO save (AccountDTO dto);
 Account findByEmail(String email);
 Account findByUserName(String userName);
=======
	 Account findById(Long id);
	 AccountDTO save(AccountDTO dto);
	 Account findByEmail(String email);
	 AccountDTO create(AccountDTO dto);
	 AccountDTO update(long id, AccountDTO dto);
	 void delete(long id);
	 List<Account> getAll(int page);
	 Account get(long id);
	 List<Account> search(String keywork);
>>>>>>> Stashed changes
}
