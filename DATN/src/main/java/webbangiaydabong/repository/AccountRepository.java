package webbangiaydabong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import webbangiaydabong.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

    Account findByUsername(String username);

    Account findByEmail(String email);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	List<Account> findByEmailLike(String keywork);

}
