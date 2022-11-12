package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import webbangiaydabong.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

    Account findByUserName(String userName);

    Account findByEmail(String email);

}
