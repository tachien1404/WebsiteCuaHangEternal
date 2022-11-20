package webbangiaydabong.repository;

<<<<<<< Updated upstream
=======
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> Stashed changes
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import webbangiaydabong.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

    Account findByUserName(String userName);

    Account findByEmail(String email);

<<<<<<< Updated upstream
=======
	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	List<Account> findByEmailLike(String keywork);
	
	List<Account> findAllByUsernameLike(String username,Pageable pageable);

>>>>>>> Stashed changes
}
