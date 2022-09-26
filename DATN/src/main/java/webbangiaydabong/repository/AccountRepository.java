package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webbangiaydabong.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	

}
