package webbangiaydabong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.Account;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long>{

    Account findByUsername(String username);

    Account findByEmail(String email);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	List<Account> findByEmailLike(String keywork);

	List<Account> findByEmailLikeOrFullnameLikeOrUsernameLike(String string, String string2, String string3);
}
