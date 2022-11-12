package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webbangiaydabong.entity.Account;
@Repository
public interface AuthenticateRepository extends JpaRepository<Account,Long> {
}
