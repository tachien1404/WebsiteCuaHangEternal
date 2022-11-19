package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

    Account findByUserName(String userName);

    Account findByEmail(String email);

}
