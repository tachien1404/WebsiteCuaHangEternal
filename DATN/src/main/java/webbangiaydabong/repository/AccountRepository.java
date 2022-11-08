package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import webbangiaydabong.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

    @Query("select a from Account a where a.email = ?1")
    Account findByEmail(String email);

}
